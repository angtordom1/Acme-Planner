package acme.features.manager.task;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.spam.SpamService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager,Task>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ManagerTaskRepository repository;
	
	@Autowired
	protected SpamService spamService;
	
	// AbstractUpdateService<Manager, Task> interface -------------------------
	
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
		result = !task.isFinished() && manager.getUserAccount().getId() == principal.getAccountId();

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

	@SuppressWarnings("deprecation")
	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if(!errors.hasErrors("periodStart")){
			final Date now = new GregorianCalendar().getTime();
			now.setSeconds(0);
			
			errors.state(request, entity.getPeriodStart().after(now), "periodStart", "manager.task.form.error.pastPeriod");
		}
		if(!errors.hasErrors("periodEnd")){
			errors.state(request, entity.getPeriodEnd().after(entity.getPeriodStart()), "periodEnd", "manager.task.form.error.period");
		}
		if(!errors.hasErrors("workload")){
			//Debe de caber dentro del tiempo de ejecución
			final Date periodStart = entity.getPeriodStart();
			final Date periodEnd = entity.getPeriodEnd();
			final double workload = entity.getWorkload();
			
			final double hoursW = Math.floor(workload);
			final double minsW = (workload-hoursW)*100;
			boolean res = false;
			
			if(periodStart != null && periodStart.before(periodEnd)) {
				final long milliseconds = Math.abs(periodEnd.getTime() - periodStart.getTime());
				final long diff = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MILLISECONDS);
				final double hours = Math.floor(diff/60.0);
				final double mins = diff%60;
				res =  (hoursW > hours) || (hoursW == hours && minsW > mins);
			}
			
			errors.state(request, !res, "workload", "manager.task.form.error.workloadFit");
			
			//Los decimales no pueden sobrepasar del 59
			final double minutes = workload - hoursW;
			errors.state(request, minutes <= 0.59, "workload", "manager.task.form.error.decimals");
		}
		if(!errors.hasErrors("title")){
			final String title = entity.getTitle();
			final List<String> spamWords = this.spamService.getSpamWordsByString(title);
			
			errors.state(request, spamWords.isEmpty(), "title", "manager.task.form.error.spam", 
				spamWords.toString().replaceAll("[\\[\\]]", ""));
		}
		if(!errors.hasErrors("description")){
			final String description = entity.getDescription();
			final List<String> spamWordsD = this.spamService.getSpamWordsByString(description);
			
			errors.state(request, spamWordsD.isEmpty(), "description", "manager.task.form.error.spam", 
				spamWordsD.toString().replaceAll("[\\[\\]]", ""));
		}
		
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
