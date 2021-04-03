package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository{
	
	@Query("select task from Task task where task.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select task from Task task where task.finished = true")
	Collection<Task> findTasksFinished();
	
}
