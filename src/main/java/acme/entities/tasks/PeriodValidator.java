package acme.entities.tasks;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodValidator implements ConstraintValidator<PeriodConstraint, Task>{

	@Override
	public boolean isValid(final Task task, final ConstraintValidatorContext context) {
		
		boolean result = true;
		final Date start = task.getPeriodStart();
		final Date end = task.getPeriodEnd();
		
		if(end.before(start)) result = false;
		
		return result;
	}

}
