package acme.entities.spam;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

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
	
	

	@NotEmpty
	@OneToMany(mappedBy = "spam", fetch = FetchType.EAGER)
	protected List<SpamWord> spamWords;
	
}
