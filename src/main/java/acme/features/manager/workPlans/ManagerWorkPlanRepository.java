package acme.features.manager.workPlans;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workPlans.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanRepository extends AbstractRepository{
	
	@Query("select wp from WorkPlan wp JOIN wp.tasks t where t.id = ?1")
	Collection<WorkPlan> findManyByTaskId(int id);

	@Query("select wp from WorkPlan wp where wp.id = ?1")
	WorkPlan findOneWorkPlanById(int id);

	@Query("SELECT wp FROM WorkPlan wp order by wp.periodStart")
	Collection<WorkPlan> findMany();

	@Query("select wp from WorkPlan wp where wp.managerId = ?1")
	Collection<WorkPlan> findManyByManager(int id);



}
