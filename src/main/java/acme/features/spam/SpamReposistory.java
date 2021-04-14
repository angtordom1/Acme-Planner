package acme.features.spam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamReposistory extends AbstractRepository{

	@Query("SELECT s FROM Spam s WHERE s.inicialLetter= ?1 ")
	Collection<String> finByLetter(char letter);
	
}
