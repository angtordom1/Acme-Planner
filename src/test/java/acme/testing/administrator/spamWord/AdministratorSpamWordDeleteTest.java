package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordDeleteTest extends AcmePlannerTest{
	
	
	//Se testea deleteService borrando un dato y esperando que este no se encuentre
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/delete.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(32)
	public void delete(final int recordIndex , final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		By locator;

		locator = By.xpath(String.format("//button[@type='button' and normalize-space()='%s']", "Show spam words"));
		super.clickAndWait(locator);
		
		super.clickOnListingRecord(recordIndex);

		
		By locator2;

		locator2 = By.xpath(String.format("//button[@type='submit' and normalize-space()='%s']", "Delete"));
		super.clickAndWait(locator2);
		
		
		By locator3;

		locator3 = By.xpath(String.format("//tr[contains(., '%s')][contains(., '%s')]", word , size));
		
		super.checkNotExists(locator3);

		
		super.signOut();
		
	}

}
