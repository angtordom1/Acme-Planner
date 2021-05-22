package acme.testing.authenticated.provider;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedProviderCreateTest extends AcmePlannerTest{

	
	//Test cases
	
	/*
	 *  Functionality to be tested: Authenticated user becomes provider with positive values (positive case)
	 *  Expected results: Authenticated user must be able to become a provider
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
	 *  Functionality to be tested: Authenticated user must not become a provider with negative values (negative case)
	 *  Expected results: Authenticated user must not become a provider
	 *  Constraints to be violated: NotBlank company and sector
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
