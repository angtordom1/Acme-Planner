package acme.entities.spam;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Range(min=1,max=100)
	protected Double umbral;
	
}
