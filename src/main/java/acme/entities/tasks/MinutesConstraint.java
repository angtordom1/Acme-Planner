package acme.entities.tasks;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MinutesValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinutesConstraint {
	String message() default "Los decimales sólo pueden llegar hasta 59 debido a que son minutos.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
