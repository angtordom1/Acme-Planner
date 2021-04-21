package acme.entities.workPlans;

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
@TaskStateConstraint //If Public, cant private tasks
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


	public Date getMinExecutionPeriod() {
		Date res = null;
		if(!this.tasks.isEmpty()) {

			res =this.tasks.stream().map(Task::getPeriodStart).min(Date::compareTo).get();

		}
		return res;
	}

	public Date getMaxExecutionPeriod() {
		Date res = null;
		if(!this.tasks.isEmpty()) {

			res =this.tasks.stream().map(Task::getPeriodEnd).max(Date::compareTo).get();

		}
		return res;
	}

}









