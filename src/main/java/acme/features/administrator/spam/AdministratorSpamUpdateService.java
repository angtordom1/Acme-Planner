package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamUpdateService implements AbstractUpdateService<Administrator, Spam>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AdministratorSpamRepository repository;
		
	// AbstractUpdateService<Administrator, Spam> interface -------------------------
	
	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);		
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "umbral","spamWords");
	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;
		
		Spam result;
		
		result = this.repository.findMany().iterator().next();
		
		return result;
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		// como tal no debería validar nada, ¿no? ya que el umbral lo valida mediante la anotación
	}

	@Override
	public void update(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
