package acme.entities.workPlans;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaskStateValidator implements ConstraintValidator<TaskStateConstraint, WorkPlan>{

	@Override
	public boolean isValid(final WorkPlan workPlan, final ConstraintValidatorContext context) {
		boolean res=true;
		if(workPlan.isState() &&
			workPlan.tasks.stream().anyMatch(x->!x.isState())) {
			res = false;
		}
		return res;
	}

}
