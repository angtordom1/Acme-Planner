package acme.testing.authenticated.consumer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedConsumerCreateTest extends AcmePlannerTest{

	
	//Test cases
	
	/*
	 *  Funcionalidad a probar: Usuario autenticado se convierte en consumidor (caso positivo)
	 *  Resultados esperados: Usuario autenticado debe poder convertirse en consumidor
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createPositive(final int recordIndex, final String company, final String sector, final String username, final String password) {
		
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Become a consumer");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.checkButtonExists("Register");
		super.clickOnSubmitButton("Register");
		
		super.checkLinkExists("Consumer");
		
		super.signOut();
	}
	
	/*
	 *  Funcionalidad a probar: Authenticated user must not become a consumer with negative values (negative case)
	 *  Resultados esperados: Authenticated user must not become a consumer
	 *  Restricciones a infringir: NotBlank de compañía y sector
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex, final String company, final String sector) {
		
		super.signIn("manager3", "manager3");
		
		super.clickOnMenu("Account", "Become a consumer");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.checkButtonExists("Register");
		super.clickOnSubmitButton("Register");
		
		super.checkErrorsExist();
		
	}
	
}
