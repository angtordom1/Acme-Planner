package acme.testing.authenticated.provider;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedProviderUpdateTest extends AcmePlannerTest{

	/*
	 *  Provider update service
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
	 *  Functionality to be tested: Authenticated provider updates its' values (positive case)
	 *  Expected results: Authenticated provider must be able to change his company and sector
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
	 *  Functionality to be tested: Authenticated provider updates its' values (negative case)
	 *  Expected results: Authenticated provider must not be able to change his company and/or sector
	 *  Constraints to be violated: NotBlank company and sector
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
