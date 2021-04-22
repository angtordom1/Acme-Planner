package acme.entities.workPlans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@TaskStateConstraint If Public, cant private tasks
public class WorkPlan extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected boolean state;


	@ManyToMany(mappedBy = "workplan")
	protected List<Task> tasks;


	// Derived attributes -----------------------------------------------------
	public Double getworkload() {
		return this.tasks.stream().
			map(Task::getWorkload).
			collect(Collectors.summingDouble(Double::doubleValue));
	}


	public LocalDateTime getMinExecutionPeriod() {
		LocalDateTime res = null;
		if(!this.tasks.isEmpty()) {

			final Date aux =this.tasks.stream().map(Task::getPeriodStart).min(Date::compareTo).get();
			final LocalDate tm=aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			res=LocalDateTime.of(tm.getYear(),tm.getMonth(),tm.getDayOfMonth()-1,8,0);

		}
		return res;
	}

	public LocalDateTime getMaxExecutionPeriod() {
		LocalDateTime res = null;
		if(!this.tasks.isEmpty()) {

			final Date aux =this.tasks.stream().map(Task::getPeriodEnd).max(Date::compareTo).get();
			final LocalDate tm=aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			res=LocalDateTime.of(tm.getYear(),tm.getMonth(),tm.getDayOfMonth()+1,17,00);

		}
		return res;
	}

}









