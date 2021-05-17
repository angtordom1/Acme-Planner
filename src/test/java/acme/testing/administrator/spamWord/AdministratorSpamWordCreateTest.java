package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordCreateTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/createPositive.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(32)
	public void createPositive(final int recordIndex , final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam's parameter");
		super.clickOnSubmitButton("Create");
		
		
		super.fillInputBoxIn("word", word);
		super.fillInputBoxIn("size", size);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Administrator", "Spam's parameter");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		super.checkColumnHasValue(recordIndex, 1, size);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);
		super.checkInputBoxHasValue("size", size);
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/createNegative.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(33)
	public void createNegative(final int recordIndex , final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam's parameter");
		super.clickOnSubmitButton("Create");
		
		
		super.fillInputBoxIn("word", word);
		super.fillInputBoxIn("size", size);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
		
	}
	
}
