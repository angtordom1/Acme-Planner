package acme.testing.authenticated.consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedConsumerUpdateTest extends AcmePlannerTest{

	
	/*
	 *  Actualización de proveedor
	 */
	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
		
		this.navigateHome();
		this.signIn("manager1","manager1");
		super.clickOnMenu("Account", "Become a consumer");

		super.fillInputBoxIn("company", "company");
		super.fillInputBoxIn("sector", "sector");
		
		super.checkButtonExists("Register");
		super.clickOnSubmitButton("Register");
		this.signOut();
	}
	
	//Test cases
	
	/*
	 *  Funcionalidad a probar: Consumidor autenticado actualiza sus valores (caso positivo)
	 *  Resultados esperados: Consumidor autenticado debe ser capaz de actualizar compañía y sector
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void updatePositive(final String company, final String sector) {
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Account", "Consumer data");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.checkButtonExists("Update");
		super.clickOnSubmitButton("Update");
		
		super.signOut();
	}
	
	/*
	 *  Funcionalidad a probar: Consumidor autenticado no debe actualizar sus valores (caso negativo)
	 *  Resultados esperados: Consumidor autenticado no debe ser capaz de actualizar compañía y sector
	 *  Restricciones a infringir: NotBlank de compañía y sector
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/consumer/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void updateNegative(final String company, final String sector) {
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Account", "Consumer data");
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", sector);
		
		super.checkButtonExists("Update");
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
	}
	
}
