package acme.entities.tasks;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = WorkloadFittingValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkloadFittingConstraint {
	String message() default "El workload debe coincidir en el periodo de ejecución";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
