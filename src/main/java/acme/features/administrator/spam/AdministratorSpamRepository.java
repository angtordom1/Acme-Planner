package acme.features.administrator.spam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamRepository extends AbstractRepository{

	@Query("SELECT s FROM Spam s")
	Collection<Spam> findMany();
	
	@Query("select s from Spam s where s.id = ?1")
	Spam findOneSpamById(int id);
}
