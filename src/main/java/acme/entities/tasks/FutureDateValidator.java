package acme.entities.tasks;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FutureDateValidator implements ConstraintValidator<FutureDateConstraint, Task>{

	@Override
	public boolean isValid(final Task task, final ConstraintValidatorContext context) {
		
		boolean result = true;
		final Date periodStart = task.getPeriodStart();
		final Date periodEnd = task.getPeriodEnd();
		final Date creationDate = task.getCreationDate();
		
		
		if(periodStart.before(creationDate)) result = false;
		
		if(periodEnd.before(creationDate) || periodEnd.before(periodStart)) result = false;
		
		
		return result;
	}

}
