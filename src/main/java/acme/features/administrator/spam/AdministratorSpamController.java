package acme.features.administrator.spam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.spam.Spam;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
public class AdministratorSpamController extends AbstractController<Administrator, Spam>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AdministratorSpamListService listService;
	
	@Autowired
	protected AdministratorSpamUpdateService updateService;
	
	@Autowired
	protected AdministratorSpamShowService showService;
	
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
