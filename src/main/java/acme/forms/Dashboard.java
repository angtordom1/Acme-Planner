package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Long						publicTasksAmount;
	Long						privateTasksAmount;
	Long						finishedTasksAmount;
	Long						unfinishedTasksAmount;
	
	Long 						workPlansAmount;
	Long 						unpublishedWorkPlansAmount;
	Long 						publishedWorkPlansAmount;
	Long						finishedWorkPlansAmount;
	Long						unfinishedWorkPlansAmount;
	
	Double						averagePeriodExecution;
	Double						deviationPeriodExecution;
	Double						minimunPeriodExecution;
	Double						maximunPeriodExecution;
	
	Double						averageWorkload;
	Double						deviationWorkload;
	Double						minimunWorkload;
	Double						maximunWorkload;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}