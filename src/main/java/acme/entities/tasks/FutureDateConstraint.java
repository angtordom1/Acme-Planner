package acme.entities.tasks;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FutureDateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureDateConstraint {
	String message() default "El periodo de ejecución debe ser en el futuro a la hora de crear la tarea";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
