package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamUpdateTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spam/updatePositive.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(30)
	public void updatePositive(final int recordIndex 	, final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam's parameter");
		
		super.fillInputBoxIn("threshold", threshold);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spam's parameter");
		
		super.checkInputBoxHasValue("threshold", threshold);
		
		super.signOut();
		
	}
}
