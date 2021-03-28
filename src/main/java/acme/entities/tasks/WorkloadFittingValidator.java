package acme.entities.tasks;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkloadFittingValidator implements ConstraintValidator<WorkloadFittingConstraint, Task>{
	
	@Override
	public boolean isValid(Task task, ConstraintValidatorContext context) {
		
		boolean result = true;
		Date periodStart = task.getPeriodStart();
		Date periodEnd = task.getPeriodEnd();
		double workload = task.getWorkload();
		
		double hoursW = Math.floor(workload);
		double minsW = (workload-hoursW)*100;
		
		long milliseconds = Math.abs(periodEnd.getTime() - periodStart.getTime());
		long diff = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MINUTES);
		double hours = Math.floor(diff/60);
		double mins = diff%60;
		
		
		if(hoursW > hours) {
			result = false;
		}else if(hoursW == hours && minsW > mins) {
			result = false;
		}
		return result;
	}

}
