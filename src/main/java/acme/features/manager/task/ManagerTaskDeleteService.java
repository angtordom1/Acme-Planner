package acme.features.manager.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.features.manager.workPlans.ManagerWorkPlanRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerTaskDeleteService implements AbstractDeleteService<Manager,Task>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;
	
	@Autowired
	protected ManagerWorkPlanRepository workPlanRepository;

	// AbstractDeleteService<Manager, Task> interface -------------------------
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		final boolean result;
		final int taskId;
		Task task;
		final Manager manager;
		Principal principal;
		
		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		manager = task.getManager();
		principal = request.getPrincipal();
		result =  manager.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"title","periodStart","periodEnd","workload","description","link","state","finished");
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		
		Task result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		final Collection<WorkPlan> workPlans= this.workPlanRepository.findManyByTaskId(entity.getId());
		if(!workPlans.isEmpty()) {
			for(final WorkPlan workPlan: workPlans) {
				workPlan.getTasks().remove(entity);
				final double workload = workPlan.getTotalWorkload(workPlan.getTasks());
				workPlan.setWorkload(workload);
				this.workPlanRepository.save(workPlan);
			}
		}
		
		
		this.repository.delete(entity);
	}
}
