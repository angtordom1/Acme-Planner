package acme.testing.authenticated.provider;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedProviderUpdateTest extends AcmePlannerTest{

	/*
	 *  Servicio para actualizar proveedor
	 */
	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
		
		this.navigateHome();
		this.signIn("manager1","manager1");
		super.clickOnMenu("Account", "Become a provider");

		super.fillInputBoxIn("company", "company");
		super.fillInputBoxIn("sector", "sector");
		
		super.checkButtonExists("Register");
		super.clickOnSubmitButton("Register");
		super.signOut();
	}
	
	//Test cases
	
	/*
	 *  Funcionalidad a probar: Proveedor autenticado actualiza sus campos (caso positivo)
	 *  Resultados esperados: Proveedor autenticado debe ser capaz de cambiar de compañía y sector
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/provider/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void updatePositive(final String company, final String sector) {
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Account", "Provider data");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.checkButtonExists("Update");
		super.clickOnSubmitButton("Update");
		
		super.signOut();
	}
	
	/*
	 *  Funcionalidad a probar: Proveedor autenticado actualiza sus campos (caso negativo)
	 *  Resultados esperados: Proveedor autenticado no debe ser capaz de cambiar de compañía y sector
	 *  Restricciones a infringir: NotBlank de compañía y sector
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/provider/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void updateNegative(final String company, final String sector) {
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Account", "Provider data");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.checkButtonExists("Update");
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
	}
	
}
