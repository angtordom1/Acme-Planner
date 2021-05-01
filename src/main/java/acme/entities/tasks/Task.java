package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@FutureDateConstraint Execution period must be in the future when the task is created
//@PeriodConstraint End cannot be before the start
//@WorkloadFittingConstraint Must have equal or less hours and minutes than the execution period
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
	protected Date periodEnd;
	
	@Digits(integer = 3, fraction = 2)
	//MinutesConstraint fraction cannot be more than 59 because it represents minutes
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
	
	// Derived attributes -----------------------------------------------------
	
	public boolean isFinished() {
		Date now;

		now = new Date();
		
		this.finished = now.after(this.periodEnd);	
		
		return this.finished;
	}
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Manager manager;




	
	
}
