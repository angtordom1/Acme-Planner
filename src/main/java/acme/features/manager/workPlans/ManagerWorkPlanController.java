package acme.features.manager.workPlans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Manager;
import acme.entities.workPlans.WorkPlan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manager/work-plan/")
public class ManagerWorkPlanController extends AbstractController<Manager, WorkPlan>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerWorkPlanListMineService	listMineService;
		
		@Autowired
		protected ManagerWorkPlanShowService	showService;
		
		@Autowired
		protected ManagerWorkPlanCreateService	createService;
		
		@Autowired
		protected ManagerWorkPlanDeleteService	deleteService;
		
		@Autowired
		protected ManagerWorkPlanUpdateService	updateService;
		
		@Autowired
		protected ManagerWorkPlanPublishService	publishService;
		
		// Constructors -----------------------------------------------------------

		@PostConstruct
		protected void initialise() {

			super.addBasicCommand(BasicCommand.SHOW, this.showService);
			super.addBasicCommand(BasicCommand.CREATE, this.createService);
			super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
			super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
			super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
			super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.publishService);
		}
}
