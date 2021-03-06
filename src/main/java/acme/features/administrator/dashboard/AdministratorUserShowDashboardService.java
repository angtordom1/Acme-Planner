package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorUserShowDashboardService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorUserShowDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,
			"publicTasksAmount", "privateTasksAmount",  
			"finishedTasksAmount", "unfinishedTasksAmount", 
			"averagePeriodExecution", "deviationPeriodExecution",
			"minimunPeriodExecution","maximunPeriodExecution",
			"averageWorkload","deviationWorkload",
			"minimunWorkload","maximunWorkload", "workPlansAmount",
			"unpublishedWorkPlansAmount", "publishedWorkPlansAmount",
			"unfinishedWorkPlansAmount", "finishedWorkPlansAmount",
			"privateWorkPlansAmount","publicWorkPlansAmount");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		final Dashboard result;
		
		Long publicTasksAmount;
		Long privateTasksAmount;
		Long finishedTasksAmount;
		Long unfinishedTasksAmount;
		
		final Long workPlansAmount;
		final Long unpublishedWorkPlansAmount;
		final Long publishedWorkPlansAmount;
		final Long unfinishedWorkPlansAmount;
		final Long finishedWorkPlansAmount;
		final Long privateWorkPlansAmount;
		final Long publicWorkPlansAmount;
		
		final Double averagePeriodExecution;
		final Double deviationPeriodExecution;
		final Double minimunPeriodExecution;
		final Double maximunPeriodExecution;
		
		final Double averageWorkload;
		final Double deviationWorkload;
		final Double minimunWorkload;
		final Double maximunWorkload;

		
		final List<Task> tasksList = this.repository.findManyPublicTasks();
		
		publicTasksAmount = this.repository.findPublicTasksAmount();
		privateTasksAmount = this.repository.findPrivateTasksAmount();
		finishedTasksAmount = this.repository.findFinishedTasksAmount();
		unfinishedTasksAmount = this.repository.findUnfinishedTasksAmount();
		
		workPlansAmount = this.repository.findWorkPlansAmount();
		unpublishedWorkPlansAmount = this.repository.findUnpublishedWorkPlansAmount();
		publishedWorkPlansAmount = this.repository.findPublishedWorkPlansAmount();
		unfinishedWorkPlansAmount = this.repository.findUnfinishedWorkPlansAmount();
		finishedWorkPlansAmount = this.repository.findFinishedWorkPlansAmount();
		privateWorkPlansAmount = this.repository.findPrivateWorkPlansAmount();
		publicWorkPlansAmount = this.repository.findPublicWorkPlansAmount();
		
		averagePeriodExecution = tasksList.stream()
			.mapToLong(x -> x.getPeriodEnd().getTime() - x.getPeriodStart().getTime())
			.average().orElse(0.0) / 3600000.0;
		
		if(tasksList.size() > 1) {
			deviationPeriodExecution = Math.sqrt(tasksList.stream()
				.mapToDouble(x -> Math.pow(x.getPeriodEnd().getTime() - x.getPeriodStart().getTime() - averagePeriodExecution, 2.0))
				.sum() / (tasksList.size() - 1)) / 3600000.0; //formula de la desviacion tipica
		} else {
			deviationPeriodExecution = 0.;
		}
		
		minimunPeriodExecution = tasksList.stream()
			.mapToLong(x -> x.getPeriodEnd().getTime() - x.getPeriodStart().getTime())
			.min().getAsLong() / 3600000.0;
		maximunPeriodExecution = tasksList.stream()
			.mapToLong(x -> x.getPeriodEnd().getTime() - x.getPeriodStart().getTime())
			.max().getAsLong() / 3600000.0;
		
		
		averageWorkload = tasksList.stream()
			.mapToDouble(x -> this.parseFromSexagesimalToPercentage(x.getWorkload()))
			.average().orElse(0.0);
		
		if(tasksList.size() > 1) {
		deviationWorkload = Math.sqrt(tasksList.stream()
			.mapToDouble(x -> Math.pow(this.parseFromSexagesimalToPercentage(x.getWorkload()) - averageWorkload, 2.0))
			.sum() / (tasksList.size() - 1));
		} else {
			deviationWorkload = 0.;
		}
		minimunWorkload = this.repository.minimunWorkload();
		maximunWorkload = this.repository.maximunWorkload();

		result = new Dashboard();
		
		result.setPublicTasksAmount(publicTasksAmount);
		result.setPrivateTasksAmount(privateTasksAmount);
		result.setFinishedTasksAmount(finishedTasksAmount);
		result.setUnfinishedTasksAmount(unfinishedTasksAmount);
		
		result.setWorkPlansAmount(workPlansAmount);
		result.setUnpublishedWorkPlansAmount(unpublishedWorkPlansAmount);
		result.setPublishedWorkPlansAmount(publishedWorkPlansAmount);
		result.setUnfinishedWorkPlansAmount(unfinishedWorkPlansAmount);
		result.setFinishedWorkPlansAmount(finishedWorkPlansAmount);
		result.setPrivateWorkPlansAmount(privateWorkPlansAmount);
		result.setPublicWorkPlansAmount(publicWorkPlansAmount);
		
		result.setAveragePeriodExecution(this.parseFromPercentageToSexagesimal(averagePeriodExecution));
		result.setDeviationPeriodExecution(this.parseFromPercentageToSexagesimal(deviationPeriodExecution));
		result.setMinimunPeriodExecution(minimunPeriodExecution);
		result.setMaximunPeriodExecution(maximunPeriodExecution);
		
		result.setAverageWorkload(this.parseFromPercentageToSexagesimal(averageWorkload));
		result.setDeviationWorkload(this.parseFromPercentageToSexagesimal(deviationWorkload));
		result.setMinimunWorkload(minimunWorkload);
		result.setMaximunWorkload(maximunWorkload);

		return result;
	}
	
	//Consists in a method that transform minutes from workload into a fraction
	private Double parseFromSexagesimalToPercentage(final double workload) {
		
		final int intPart = (int) workload;
		final double doubPartToPercentage = (workload - intPart) * 100/60;
		
		return intPart + doubPartToPercentage;
	}
	
	private Double parseFromPercentageToSexagesimal(final double workload) {
		
		final int intPart = (int) workload;
		final double doubPartToPercentage = (workload - intPart) * 60/100;
		
		return intPart + doubPartToPercentage;
	}

}
