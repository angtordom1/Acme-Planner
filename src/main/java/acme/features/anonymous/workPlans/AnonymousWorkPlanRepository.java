package acme.features.anonymous.workPlans;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workPlans.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkPlanRepository extends AbstractRepository {

	@Query("SELECT wp FROM WorkPlan wp where wp.state = true and wp.finished = false")
	Collection<WorkPlan> findManyPublicUnfinished();

}