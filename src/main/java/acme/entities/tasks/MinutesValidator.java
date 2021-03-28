package acme.entities.tasks;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinutesValidator implements ConstraintValidator<MinutesConstraint, Task>{

	@Override
	public boolean isValid(Task task, ConstraintValidatorContext context) {
		
		boolean result = true;
		double workload = task.getWorkload();
		double minutes = workload - Math.floor(workload);
		
		if(minutes > 0.59 ) result = false;
		
		return result;
	}

}
