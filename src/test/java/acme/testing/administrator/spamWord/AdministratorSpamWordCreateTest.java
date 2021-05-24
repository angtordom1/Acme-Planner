package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordCreateTest extends AcmePlannerTest{
	
	//Se testea createService creamos varios spamwords y esperamos que se muestren
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/createPositive.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(32)
	public void createPositive(final int recordIndex , final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		By locator;

		locator = By.xpath(String.format("//button[@type='button' and normalize-space()='%s']", "Show spam words"));
		super.clickAndWait(locator);
		
		By locator2;

		locator2 = By.xpath(String.format("//button[@type='button' and normalize-space()='%s']", "Create"));
		super.clickAndWait(locator2);
		
		super.fillInputBoxIn("word", word);
		super.fillInputBoxIn("size", size);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		super.clickAndWait(locator);
		
		
		super.checkColumnHasValue(recordIndex, 0, word);
		super.checkColumnHasValue(recordIndex, 1, size);
		
		super.clickOnListingRecord(recordIndex);
		
		
		super.checkInputBoxHasValue("word", word);
		super.checkInputBoxHasValue("size", size);
		
		super.signOut();
		
	}
	
	//Se testea createService creamos varios spamwords con incorrectamente(numero de palabras incorrecto y campos sin rellenar)
	//y esperamos que aparezcan fallos
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/createNegative.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(33)
	public void createNegative(final int recordIndex , final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		By locator;

		locator = By.xpath(String.format("//button[@type='button' and normalize-space()='%s']", "Show spam words"));
		super.clickAndWait(locator);
		
		By locator2;

		locator2 = By.xpath(String.format("//button[@type='button' and normalize-space()='%s']", "Create"));
		super.clickAndWait(locator2);
		
		
		super.fillInputBoxIn("word", word);
		super.fillInputBoxIn("size", size);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
		
	}
	
}
