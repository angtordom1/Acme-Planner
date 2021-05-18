package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordUpdateTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/updatePositive.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(34)
	public void updatePositive(final int recordIndex , final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam's parameter");
		super.clickOnSubmitButton("Show spam words");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("word", word);
		super.fillInputBoxIn("size", size);
		super.clickOnSubmitButton("Update");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		super.checkColumnHasValue(recordIndex, 1, size);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);
		super.checkInputBoxHasValue("size", size);
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/updateNegative.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(34)
	public void updateNegative(final int recordIndex , final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam's parameter");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("word", word);
		super.fillInputBoxIn("size", size);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
		
	}
}
