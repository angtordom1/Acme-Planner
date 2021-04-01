package acme.entities.tasks;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PeriodValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PeriodConstraint {
	String message() default "El final del periodo no puede ser antes que el comienzo.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
