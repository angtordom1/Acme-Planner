package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamShowTest extends AcmePlannerTest {

	//Se testea showService, esperamos que se muestren correctamente los fallos
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spam/showPositive.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(31)
	public void showPositive(final int recordIndex 	, final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		super.checkInputBoxHasValue("umbral", threshold);
		
		super.signOut();
		
	}
	

	
}
