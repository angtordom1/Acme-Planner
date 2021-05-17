package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordListTest extends AcmePlannerTest{
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/list.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(31)
	public void list(final int recordIndex 	, final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam's parameter");
		
		super.checkColumnHasValue(recordIndex, 0, "word");
		super.checkColumnHasValue(recordIndex, 1, "size");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);
		super.checkInputBoxHasValue("size", size);
		
		super.signOut();
		
	}
}
