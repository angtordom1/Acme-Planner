package acme.entities.spam;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

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

	@Range(min=1,max=100)
	protected Double umbral;
	
	
	
	@ElementCollection(targetClass=String.class)
	protected List<String> spamWords;
	
	
}
