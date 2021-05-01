package acme.features.manager.workPlans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.features.spam.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanPublishService implements AbstractUpdateService<Manager,WorkPlan>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ManagerWorkPlanRepository repository;
	
	@Autowired
	protected SpamService spamService;
	
	// AbstractUpdateService<Manager, WorkPlan> interface -------------------------
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
	assert request != null;
		
		boolean result;
		int id;
		WorkPlan workplan;
		
		id = request.getModel().getInteger("id");
		workplan = this.repository.findOneWorkPlanById(id);
		result = workplan.isState() && !workplan.isFinished() && 
			workplan.getTasks().get(0).getManager().getId()==request.getPrincipal().getAccountId();
		
		return result;
	}

	@Override
	public void bind(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "periodStart", "periodEnd", "workload", "state", "finished");
		}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;
		
		WorkPlan result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkPlanById(id);
		
		return result;
	}


	@Override
	public void validate(final Request<WorkPlan> request, final WorkPlan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
		if(!errors.hasErrors("tasks")){
			final List<Task> tasks = entity.getTasks();
			List<Task> badTasks=new ArrayList<Task>();
			if(entity.isState()) {
				badTasks = tasks.stream().filter(x->!x.isState()).collect(Collectors.toList());
			}
			errors.state(request, badTasks.isEmpty(), "tasks", "manager.workplan.form.error.badtasks", 
				badTasks.toString());
		}
		
		if(!errors.hasErrors("manager")){
			final List<Task> tasks = entity.getTasks();
			final List<Task> badTasks= tasks.stream().filter(x->x.getManager().getId()!=request.getPrincipal()
				.getAccountId()).collect(Collectors.toList());
			
			errors.state(request, badTasks.isEmpty(), "manager", "manager.workplan.form.error.manager", 
				badTasks.toString());
		}
		
		if(!errors.hasErrors("state")){
			final Boolean publicated=entity.isState();
			
			errors.state(request, !publicated, "state", "manager.workplan.form.error.state");
		}
		
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;

		entity.setState(true);
		this.repository.save(entity);
	}

}
