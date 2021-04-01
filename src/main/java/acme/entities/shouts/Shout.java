package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shout extends DomainEntity {



	protected static final long	serialVersionUID	= 1L;



	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				moment;

	@NotBlank
	@Range(min=5, max=25)
	protected String			author;

	@NotBlank
	@Range(max=100)
	protected String			text;
	
	@URL
	protected String			info;

}
