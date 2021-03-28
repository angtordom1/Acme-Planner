package acme.entities.tasks;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FutureDateValidator implements ConstraintValidator<FutureDateConstraint, Task>{

	@Override
	public boolean isValid(Task task, ConstraintValidatorContext context) {
		
		boolean result = true;
		Date periodStart = task.getPeriodStart();
		Date periodEnd = task.getPeriodEnd();
		Date creationDate = task.getCreationDate();
		
		
		if(periodStart.before(creationDate) || periodEnd.before(creationDate)) result = false;
		
		
		return result;
	}

}
