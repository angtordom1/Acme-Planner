package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorUserShowDashboardRepository extends AbstractRepository{
	
	@Query("select count(t) from Task t where t.state = true")
	Long findPublicTasksAmount();
	
	@Query("select count(t) from Task t where t.state = false")
	Long findPrivateTasksAmount();
	
	@Query("select count(t) from Task t where t.finished = true")
	Long findFinishedTasksAmount();
	
	@Query("select count(t) from Task t where t.finished = false")
	Long findUnfinishedTasksAmount();
	
	@Query("select min(t.workload) from Task t")
	Double minimunWorkload();
	
	@Query("select max(t.workload) from Task t")
	Double maximunWorkload();
	
	@Query("select t from Task t")
	List<Task> findManyPublicTasks();
	
	@Query("select count(w) from WorkPlan w")
	Long findWorkPlansAmount();
	
	@Query("select count(w) from WorkPlan w where w.state = false")
	Long findUnpublishedWorkPlansAmount();
	
	@Query("select count(w) from WorkPlan w where w.state = true")
	Long findPublishedWorkPlansAmount();
	
	@Query("select count(w) from WorkPlan w where w.finished = false")
	Long findUnfinishedWorkPlansAmount();
	
	@Query("select count(w) from WorkPlan w where w.finished = true")
	Long findFinishedWorkPlansAmount();
	
}