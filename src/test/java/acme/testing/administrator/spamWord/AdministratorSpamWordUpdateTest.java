package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordUpdateTest extends AcmePlannerTest{
	
	//Se testea updateService actualizamos varios datos y esperamos que se muestren estos con los cambios
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/updatePositive.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(34)
	public void updatePositive(final int recordIndex , final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		By locator;

		locator = By.xpath(String.format("//button[@type='button' and normalize-space()='%s']", "Show spam words"));
		super.clickAndWait(locator);
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("word", word);
		super.fillInputBoxIn("size", size);
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		super.clickAndWait(locator);
		
		super.checkColumnHasValue(recordIndex, 0, word);
		super.checkColumnHasValue(recordIndex, 1, size);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);
		super.checkInputBoxHasValue("size", size);
		
		super.signOut();
		
	}
	
	//Se testea updateService actualizamos varios datos de manera incorrecta(numero de palabras incorrecto y campos sin rellenar)
	//y esperamos que haya fallos
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/updateNegative.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(34)
	public void updateNegative(final int recordIndex , final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		By locator;

		locator = By.xpath(String.format("//button[@type='button' and normalize-space()='%s']", "Show spam words"));
		super.clickAndWait(locator);
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("word", word);
		super.fillInputBoxIn("size", size);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
		
	}
}
