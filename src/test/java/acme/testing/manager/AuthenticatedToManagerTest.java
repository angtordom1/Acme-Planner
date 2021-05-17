package acme.testing.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedToManagerTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	/*
	 * TEST DE ALTA DE MANAGERS: Este caso de prueba comprobará que la funcionalidad de alta de managers funciona correctamente tras registrarnos como usuarios de la aplicación.
	 * RESULTADO ESPERADO: Las cuentas asociadas a los usuarios utilizados deben pasar a tener permisos de manager.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/managermaker.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void authToManager(final String username, final String password, final String name, final String surname, final String email) {
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Become a manager");
		
		super.clickOnSubmitButton("Register");
		
		super.checkButtonExists("Manager");
		
		super.signOut();		
	}

}
