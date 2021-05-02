package acme.features.manager.workPlans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
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
			
			final Collection<WorkPlan> result = new ArrayList<WorkPlan>();
			Principal principal;
			
			principal = request.getPrincipal();
			final Collection<WorkPlan> workplans = this.repository.findMany();
			
			for (final Iterator<WorkPlan> iterator = workplans.iterator(); iterator.hasNext();) {
				final WorkPlan workPlan = iterator.next();
				final List<Task> tasks=workPlan.getTasks();
				if(!tasks.isEmpty() && tasks.get(0).getManager().getUserAccount().getId()==
					principal.getAccountId()	) {
					result.add(workPlan);
				}
			}
				
			
			return result;
		}
	
}
