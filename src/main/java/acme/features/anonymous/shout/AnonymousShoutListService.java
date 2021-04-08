package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousShoutRepository repository;


	// AbstractListService<Anonymous, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");
	}

	@Override
	public Collection<Shout> findMany(final Request<Shout> request) {
		assert request != null;

		final Collection<Shout> result;
		
		final int dia=LocalDate.now().getDayOfYear();
		
		result = this.repository.findMany().stream().
			filter(x->dia-(x.getMoment().getTime()/(1000*60*60*24))+(LocalDate.now().getYear()-1970)*365.25<=30).
			sorted(Comparator.comparing(Shout::getMoment,Comparator.nullsLast(Comparator.reverseOrder()))).
			collect(Collectors.toList());

		return result;
	}

}
