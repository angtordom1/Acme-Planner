package acme.features.manager.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.features.manager.workPlans.ManagerWorkPlanRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager,Task>{
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkPlanRepository workPlanRepository;
	
	@Autowired
	protected ManagerTaskRepository repository;

	// AbstractShowService<Manager,Task> interface ---------------------------
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		boolean result;
		int taskId;
		Task task;
		Manager manager;
		Principal principal;

		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		manager = task.getManager();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final Collection<WorkPlan> planes = this.workPlanRepository.findManyByTaskId(entity.getId());

        final Boolean plan = planes.stream().anyMatch(WorkPlan::isPublished);
        final Boolean plan2 = planes.stream().anyMatch(x->x.getTasks().size()==1);

        model.setAttribute("workplanPublicado", plan);
        model.setAttribute("ultimaTask",plan2);

		
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


}
