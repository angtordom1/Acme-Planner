package acme.entities.tasks;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodValidator implements ConstraintValidator<PeriodConstraint, Task>{

	@Override
	public boolean isValid(Task task, ConstraintValidatorContext context) {
		
		boolean result = true;
		Date start = task.getPeriodStart();
		Date end = task.getPeriodEnd();
		
		if(end.before(start)) result = false;
		
		return result;
	}

}
