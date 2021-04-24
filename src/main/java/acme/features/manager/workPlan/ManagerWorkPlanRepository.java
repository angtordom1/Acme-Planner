package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workPlans.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanRepository extends AbstractRepository{
	
	@Query("select wp from WorkPlan wp JOIN wp.tasks t where t.id = ?1")
	Collection<WorkPlan> findManyByTaskId(int id);

}
