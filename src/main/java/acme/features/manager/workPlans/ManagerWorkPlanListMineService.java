package acme.features.manager.workPlans;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workPlans.WorkPlan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class ManagerWorkPlanListMineService implements AbstractListService<Manager, WorkPlan>{

	// Internal state ------------------------------------------------------
	
		@Autowired
		ManagerWorkPlanRepository repository;
		
		
		// AbstractListService<Manager, WorkPlan> interface ------------------
		
		@Override
		public boolean authorise(final Request<WorkPlan> request) {
			assert request != null;
			return true;
		}

		@Override
		public void unbind(final Request<WorkPlan> request, final WorkPlan entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model,  "periodStart", "periodEnd", "workload");
		}

		@Override
		public Collection<WorkPlan> findMany(final Request<WorkPlan> request) {
			assert request != null;
			
			Collection<WorkPlan> result;
			Principal principal;
			
			principal = request.getPrincipal();
			result = this.repository.findManyByManager(principal.getActiveRoleId());
			
			return result;
		}
	
}
