package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateTest extends AcmePlannerTest{

	
	//Test cases
	
	/*
	 *  Funcionalidad a probar: Usuario autenticado se convierte en manager (caso positivo)
	 *  Resultados esperados: Usuario autenticado debe ser capaz de convertirse en manager
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String username, final String password, final String name,
		final String surname, final String email) {
		
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Become a manager");
		
		super.checkButtonExists("Register");
		super.clickOnSubmitButton("Register");
		
		super.checkLinkExists("Manager");
		
		super.signOut();
	}

	/*
	 *  Funcionalidad a probar: Manager autenticado no debe convertirse en manager dos veces (caso negativo)
	 *  Resultados esperados: Manager autenticado no debe ser capaz de convertirse en manager dos veces
	 *  Restricciones a infringir: Ninguna
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final String username, final String password) {
		
		super.signIn(username, password);
		super.checkLinkExists("Manager");
		super.signOut();
	}
	
}
