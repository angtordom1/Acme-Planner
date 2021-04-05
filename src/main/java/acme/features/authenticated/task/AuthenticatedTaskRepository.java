package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.state = true and t.finished = true order by t.periodStart")
	Collection<Task> findManyPublicFinished();

}
