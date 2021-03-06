package acme.features.manager.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository{

	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);

	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);

	@Query("select t from Task t where t.manager.id = ?1 order by t.periodStart")
	Collection<Task> findManyByManagerId(int activeRoleId);
	
	@Query("select t from Task t where t.manager.id = ?1 and t.finished = false and t.periodStart>?2 order by t.periodStart")
	Collection<Task> findManyByManagerIdAndUnfinished(int activeRoleId, Date moment);

}
