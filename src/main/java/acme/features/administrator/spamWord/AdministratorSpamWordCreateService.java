package acme.features.administrator.spamWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.entities.spam.SpamWord;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorSpamWordCreateService implements AbstractCreateService<Administrator, SpamWord>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AdministratorSpamWordRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;
	
	// AbstractCreateService<Administrator, SpamWord> interface ---------------

	@Override
	public boolean authorise(final Request<SpamWord> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);		
	}

	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "word", "size");
	}

	@Override
	public SpamWord instantiate(final Request<SpamWord> request) {
		assert request != null;
		
		Spam spam;
		SpamWord result;
		
		spam = this.spamRepository.findMany().iterator().next();
		result = new SpamWord();
		result.setSpam(spam);
		
		return result;
	}

	@Override
	public void validate(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		// SIZE: comprobar que el tamaño introducido como parámetro coincide con el de la palabra
		final String s = entity.getWord();
		final int tam = s.split(" ").length;
		final int t = entity.getSize();
		
		errors.state(request, tam == t, "size", "administrator.spamWord.form.error.size");
	}

	@Override
	public void create(final Request<SpamWord> request, final SpamWord entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
