package acme.testing.authenticated.provider;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedProviderCreateTest extends AcmePlannerTest{

	
	//Test cases
	
	/*
	 *  Funcionalidad a probar: Usuario autenticado se convierte en proveedor (caso positivo)
	 *  Resultados esperados: Usuario autenticado debe ser capaz de convertirse en proveedor
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/provider/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createPositive(final int recordIndex, final String company, final String sector, final String username, final String password) {
		
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Become a provider");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.checkButtonExists("Register");
		super.clickOnSubmitButton("Register");
		
		super.checkLinkExists("Provider");
		
		super.signOut();
	}
	
	/*
	 *  Funcionalidad a probar: Usuario autenticado no debe convertirse en proveedor (caso negativo)
	 *  Resultados esperados: Usuario autenticado no debe ser capaz de convertirse en proveedor
	 *  Restricciones a infringir: NotBlank de compañía y sector
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/provider/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative(final int recordIndex, final String company, final String sector) {
		
		super.signIn("manager3", "manager3");
		
		super.clickOnMenu("Account", "Become a provider");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.checkButtonExists("Register");
		super.clickOnSubmitButton("Register");
		
		super.checkErrorsExist();
		
	}
	
}
