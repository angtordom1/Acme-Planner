package acme.entities.tasks;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkloadFittingValidator implements ConstraintValidator<WorkloadFittingConstraint, Task>{
	
	@Override
	public boolean isValid(final Task task, final ConstraintValidatorContext context) {
		
		boolean result = true;
		final Date periodStart = task.getPeriodStart();
		final Date periodEnd = task.getPeriodEnd();
		final double workload = task.getWorkload();
		
		final double hoursW = Math.floor(workload);
		final double minsW = (workload-hoursW)*100;
		
		final long milliseconds = Math.abs(periodEnd.getTime() - periodStart.getTime());
		final long diff = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MILLISECONDS);
		final double hours = Math.floor(diff/60);
		final double mins = diff%60;
		
		
		if(hoursW > hours) {
			result = false;
		}else if(hoursW == hours && minsW > mins) {
			result = false;
		}
		return result;
	}

}
