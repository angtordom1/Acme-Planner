package acme.entities.workPlans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity{

	private static final long serialVersionUID = 1L;

	protected boolean state;


	@ManyToMany(mappedBy = "workplan")
	protected List<Task> tasks;


	// Derived attributes -----------------------------------------------------
	public Double getworkload() {
		double minute = 0.00;
		double hour = 0.00;
		final List<Task> taskList = this.tasks;
		for (int i = 0; i<taskList.size(); i++) {
			final Task task = taskList.get(i);
			final double workload  = task.getWorkload();
			final double hoursW = Math.floor(workload);
			final double minutes = workload - hoursW;
			minute+=minutes;
			hour+= hoursW;
			
			if(minute>=0.60) {
				hour++;
				minute=minute-0.60;
			}		
		}
		
		return hour+minute;
		
	}


	public LocalDateTime getMinExecutionPeriod() {
		LocalDateTime res = null;
		if(!this.tasks.isEmpty()) {

			final Date aux =this.tasks.stream().map(Task::getPeriodStart).min(Date::compareTo).orElse(new Date());
			final LocalDate tm = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			res=LocalDateTime.of(tm.getYear(),tm.getMonth(),tm.getDayOfMonth()-1,8,0);

		}
		return res;
	}

	public LocalDateTime getMaxExecutionPeriod() {
		LocalDateTime res = null;
		if(!this.tasks.isEmpty()) {

			final Date aux =this.tasks.stream().map(Task::getPeriodEnd).max(Date::compareTo).orElse(new Date());
			final LocalDate tm=aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			res=LocalDateTime.of(tm.getYear(),tm.getMonth(),tm.getDayOfMonth()+1,17,00);

		}
		return res;
	}

}

