package acme.entities.tasks;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinutesValidator implements ConstraintValidator<MinutesConstraint, Double>{


	@Override
	public boolean isValid(Double workload, ConstraintValidatorContext context) {
		boolean result = true;
		double minutes = workload - Math.floor(workload);
		
		if(minutes > 0.59 ) result = false;
		
		return result;
	}

}
