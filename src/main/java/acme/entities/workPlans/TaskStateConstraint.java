package acme.entities.workPlans;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = TaskStateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskStateConstraint {
	String message() default "En un WorkSpace ninguna Task debe ser privada";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
