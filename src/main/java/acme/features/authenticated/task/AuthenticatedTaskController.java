package acme.features.authenticated.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

public class AuthenticatedTaskController extends AbstractController<Authenticated, Task>{
	
	// Internal state ------------------------------------------------------
	
	@Autowired
	AuthenticatedTaskListService listService;
	
	// Constructors --------------------------------------------------------
	
	@PostConstruct
	public void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
