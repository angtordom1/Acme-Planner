package acme.entities.workPlans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkPlan extends DomainEntity{

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;
		
	// Attributes -------------------------------------------------------------
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date periodStart;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date periodEnd;
	
	@Digits(integer = 3, fraction = 2)
	protected double workload;
	
	//If true task is public else task is private
	protected boolean state;
	
	//If true task is finished else task is not finished
	protected boolean finished;

	// Derived attributes -----------------------------------------------------
	
	public Double getTotalWorkload(final List<Task> taskList) {
		double minute = 0.00;
		double hour = 0.00;
		for (int i = 0; i<taskList.size(); i++) {
			final Task task = taskList.get(i);
			final double taskWorkload  = task.getWorkload();
			final double hoursW = Math.floor(taskWorkload);
			final double minutes = taskWorkload - hoursW;
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
	
	public boolean isFinished() {
		Date now;
		boolean res;
		now = new Date();
		
		res = this.finished || now.after(this.periodEnd);	
		this.setFinished(res);
		
		return res;
	}
	
	// Relationships ----------------------------------------------------------

	@ManyToMany(fetch = FetchType.EAGER)
	protected List<Task> tasks;


}

