package acme.entities.workPlans;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
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
	
	protected Integer managerId;
	
	//If true task is public else task is private
	protected boolean state;
	
	//If true task is finished else task is not finished
	protected boolean finished;
	
	protected boolean published;

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
	
	public boolean isFinished() {
		boolean res;
		boolean aux;
		
		aux = true;
		for(final Task t : this.tasks) {
			boolean acabado;
			acabado = t.isFinished();
			aux = aux && acabado;
		}
		res = this.finished || aux;	
		this.setFinished(res);
		
		return res;
	}
	
	// Relationships ----------------------------------------------------------

	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER)
	protected List<Task> tasks;


}

