package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordListTest extends AcmePlannerTest{
	
	//Se testea listService cuyo unico objetivo es comprobar que los datos se muetran correctamente
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/list.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(31)
	public void list(final int recordIndex 	, final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		

		By locator;

		locator = By.xpath(String.format("//button[@type='button' and normalize-space()='%s']", "Show spam words"));
		super.clickAndWait(locator);
			
		
		
		super.checkColumnHasValue(recordIndex, 0, word);
		super.checkColumnHasValue(recordIndex, 1, size);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);
		super.checkInputBoxHasValue("size", size);
		
		super.signOut();
		
	}
}
