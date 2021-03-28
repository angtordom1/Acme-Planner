package acme.features.manager.userAcount;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
import acme.framework.entities.UserAccount;
import acme.framework.entities.UserAccountStatus;
import acme.framework.entities.UserRole;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerUserAccountUpdateService implements AbstractUpdateService<Manager, UserAccount> {

	
	@Autowired
	protected ManagerUserAccountRepository repository;	
	


	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind( final Request<UserAccount> request,  final UserAccount entity,  final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		StringBuilder buffer;
		Collection<UserRole> roles;

		request.unbind(entity, model, "username", "identity.name", "identity.surname", "identity.email");

		roles = entity.getRoles();
		buffer = new StringBuilder();
		for (final UserRole role : roles) {
			buffer.append(role.getAuthorityName());
			buffer.append(" ");
		}

		model.setAttribute("roleList", buffer.toString());

		if (entity.isEnabled()) {
			model.setAttribute("status", UserAccountStatus.ENABLED);
		} else {
			model.setAttribute("status", UserAccountStatus.DISABLED);
		}
	}

	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;

		UserAccount result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneUserAccountById(id);

		return result;
	}

	@Override
	public void validate(final Request<UserAccount> request, final UserAccount entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<UserAccount> request, final UserAccount entity) {
		assert request != null;
		assert entity != null;

		if (request.getModel().getString("newStatus").equals("ENABLED")) {
			entity.setEnabled(true);
		} else if (request.getModel().getString("newStatus").equals("DISABLED")) {
			entity.setEnabled(false);
		}

		this.repository.save(entity);
	}

	
}
