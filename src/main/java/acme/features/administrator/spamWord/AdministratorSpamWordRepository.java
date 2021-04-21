package acme.features.administrator.spamWord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamWordRepository extends AbstractRepository{
	
	@Query("SELECT s FROM SpamWord s")
	Collection<SpamWord> findMany();
	
	@Query("select s from SpamWord s where s.id = ?1")
	SpamWord findOneSpamWordById(int id);

}
