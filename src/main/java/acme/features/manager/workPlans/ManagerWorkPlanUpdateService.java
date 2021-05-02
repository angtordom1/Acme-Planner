package acme.features.manager.workPlans;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workPlans.WorkPlan;
import acme.features.manager.task.ManagerTaskRepository;
import acme.features.spam.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanUpdateService implements AbstractUpdateService<Manager,WorkPlan>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ManagerWorkPlanRepository repository;
	
	@Autowired
	protected ManagerTaskRepository taskRepository;
	
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
		result = workplan.getTasks().get(0).getManager().getId()==request.getPrincipal().getActiveRoleId();
		
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
		
		
		request.unbind(entity, model, "periodStart", "periodEnd", "workload", "state","finished", "tasks");
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

	@SuppressWarnings("deprecation")
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
			if(ps!=-1) {
				final String id = cadena.subSequence(ps+3, ps+6).toString();
				final Task task = this.taskRepository.findOneTaskById(Integer.parseInt(id));
				newTasks.add(task);
			}
		}
		entity.setTasks(newTasks);
		
		if(!errors.hasErrors("periodStart")){
			final Date now = new GregorianCalendar().getTime();
			now.setSeconds(0);

			errors.state(request, entity.getPeriodStart().after(now), "periodStart", "manager.work-plan.form.error.pastPeriod");
			
			final Date aux =entity.getTasks().stream().map(Task::getPeriodStart).min(Date::compareTo).orElse(new Date());
			errors.state(request, entity.getPeriodStart().before(aux), "periodStart", "manager.work-plan.form.error.periodStartTask");

		}

		if(!errors.hasErrors("periodEnd")){
			final Date now = new GregorianCalendar().getTime();
			now.setSeconds(0);

			errors.state(request, entity.getPeriodEnd().after(now), "periodEnd", "manager.work-plan.form.error.pastPeriod");
			
			errors.state(request, entity.getPeriodEnd().after(entity.getPeriodStart()), "periodEnd", "manager.work-plan.form.error.period");
			
			final Date aux =entity.getTasks().stream().map(Task::getPeriodEnd).max(Date::compareTo).orElse(new Date());
			errors.state(request, entity.getPeriodEnd().after(aux), "periodEnd", "manager.work-plan.form.error.periodEndTask");
		}

		if(!errors.hasErrors("tasks")){
			final List<Task> tasks = entity.getTasks();
			List<Task> badTasks=new ArrayList<Task>();

			if(entity.isState()) {
				badTasks = tasks.stream().filter(x->!x.isState()).collect(Collectors.toList());
			}

			errors.state(request, badTasks.isEmpty(), "tasks", "manager.work-plan.form.error.badtasks", 
				badTasks);


			final List<Task> badTasks2 = new ArrayList<Task>();
			final Principal principal = request.getPrincipal();
			final Integer idPrincipal = principal.getActiveRoleId();

			for(final Task task: tasks) {
				final Integer idManager = task.getManager().getId();
				if(!idManager.equals(idPrincipal)) badTasks.add(task);
			}

			errors.state(request, badTasks2.isEmpty(), "tasks", "manager.work-plan.form.error.tasks", 
				badTasks);
		}
	}

	@Override
	public void update(final Request<WorkPlan> request, final WorkPlan entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
