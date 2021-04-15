package acme.features.spam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamReposistory extends AbstractRepository{

	@Query("SELECT s FROM Spam s")
	List<String> getSpamWords();
	
	@Query("SELECT umbral FROM Spam s")
	Double getUmbral();
}
