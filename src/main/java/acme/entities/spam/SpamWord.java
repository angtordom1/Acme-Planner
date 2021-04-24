package acme.entities.spam;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

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
	
	@NotBlank
	@Length(min = 0, max =200)
	protected String word;
	
	protected Integer size;
	
	@ManyToOne
	protected Spam spam;

	@Override
	public String toString() {
		return this.word;
	}
	
	
}
