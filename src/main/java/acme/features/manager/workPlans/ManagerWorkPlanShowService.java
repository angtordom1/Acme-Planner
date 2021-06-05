package acme.features.manager.workPlans;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkPlanShowService implements AbstractShowService<Manager, WorkPlan>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ManagerWorkPlanRepository repository;

	@Autowired
	protected ManagerTaskRepository taskRepository;
	
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
	
	// AbstractShowService<Manager, WorkPlan> interface --------------------------
	
	@Override
	public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Principal principal;
		principal = request.getPrincipal();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		final Collection<Task> tasks = this.taskRepository.findManyByManagerIdAndUnfinished(principal.getActiveRoleId(), moment);
		for(int i = 0; i < entity.getTasks().size(); i++) {
			final Task task = entity.getTasks().get(i);
			if(!tasks.contains(task)) tasks.add(task);
		}
		model.setAttribute("allTasks",tasks.stream().collect(Collectors.toList()));
		
		request.unbind(entity, model, "periodStart", "periodEnd", "workload", "state", "tasks", "finished", "published");
	}

	@Override
	public WorkPlan findOne(final Request<WorkPlan> request) {
		assert request != null;
		
		WorkPlan result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkPlanById(id);
		
		if(result.isFinished()) {
			this.repository.save(result);
		}
		
		return result;
	}	
}