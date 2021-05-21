package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamUpdateTest extends AcmePlannerTest{
	
	//Se testea updateService actualizamos el umbral y esperamos que se mantenga el cambio
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spam/updatePositive.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(30)
	public void updatePositive(final int recordIndex 	, final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		super.fillInputBoxIn("umbral", threshold);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		super.checkInputBoxHasValue("umbral", threshold);
		
		super.signOut();
		
	}
}
