package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@FutureDateConstraint //Execution period must be in the future when the task is created
public class Task extends DomainEntity{

	// Serialisation identifier -----------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Length(min = 0, max = 80)
	protected String title;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date periodStart;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@PeriodConstraint //End cannot be before the start
	protected Date periodEnd;
	
	@Digits(integer = 3, fraction = 2)
	@WorkloadFittingConstraint //Must have equal or less hours and minutes than the execution period
	@MinutesConstraint //fraction cannot be more than 59 because it represents minutes
	protected double workload;
	
	@NotBlank
	@Length(min = 0, max = 500)
	protected String description;
	
	@URL
	protected String link;
	
	//If true task is public else task is private
	protected boolean state;
	
	//If true task is finished else task is not finished
	protected boolean finished;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
