package acme.features.spam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamReposistory extends AbstractRepository{

	@Query("SELECT s FROM SpamWord s")
	List<SpamWord> getSpamWords();
	
	@Query("SELECT umbral FROM Spam s")
	Double getUmbral();
}
