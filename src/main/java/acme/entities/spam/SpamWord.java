package acme.entities.spam;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamWord extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String word;
	
	protected Integer size;
	
	@ManyToOne
	protected Spam spam;
	
}
