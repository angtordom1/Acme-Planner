package acme.entities.spam;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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
	protected char inicialLetter;
	
	@ElementCollection(targetClass=String.class)
	protected Collection<String> spamWords;
	
	
}
