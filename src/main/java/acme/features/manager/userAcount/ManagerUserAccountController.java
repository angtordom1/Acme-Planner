package acme.features.manager.userAcount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.UserAccount;

@Controller
@RequestMapping("/manager/user-account/")
public class ManagerUserAccountController extends AbstractController<Manager, UserAccount> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerUserAccountListService	listService;

		@Autowired
		protected ManagerUserAccountShowService	showService;

		@Autowired
		protected ManagerUserAccountUpdateService	updateService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.LIST, this.listService);
			super.addBasicCommand(BasicCommand.SHOW, this.showService);
			super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		}

}
