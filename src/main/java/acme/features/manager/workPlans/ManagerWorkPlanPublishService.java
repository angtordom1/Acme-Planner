package acme.features.manager.workPlans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.features.manager.task.ManagerTaskRepository;
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
	protected ManagerTaskRepository taskRepository;
	

	
	// AbstractUpdateService<Manager, WorkPlan> interface -------------------------
	
	@Override
	public boolean authorise(final Request<WorkPlan> request) {
	assert request != null;
		
		boolean result;
		int id;
		WorkPlan workplan;
		
		id = request.getModel().getInteger("id");
		workplan = this.repository.findOneWorkPlanById(id);
		result = workplan.getManagerId()==request.getPrincipal().getActiveRoleId();
		
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
		
		request.unbind(entity, model, "state");
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
		
		request.getModel().setAttribute("tasks", this.taskRepository.findManyByManagerIdAndUnfinished(request.getPrincipal().getActiveRoleId()));
		
		final List<Task> ids = entity.getTasks();
		final List<Task> newTasks = new ArrayList<Task>();

		for (int i = 0; i < ids.size(); i++) {
			final Object object = ids.get(i);
			final String cadena = object.toString();
			final int ps = cadena.indexOf("id");
			final String id = cadena.subSequence(ps+3, ps+6).toString();
			final Task task = this.taskRepository.findOneTaskById(Integer.parseInt(id));
			newTasks.add(task);
		}
		entity.setTasks(newTasks);
		
		if(!errors.hasErrors("tasks")){
			final List<Task> tasks = entity.getTasks();
			final List<Task> badTasks = tasks.stream().filter(x->!x.isState()).collect(Collectors.toList());
			

			errors.state(request, badTasks.isEmpty(), "state", "manager.work-plan.form.error.badtasks", 
				badTasks);
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
