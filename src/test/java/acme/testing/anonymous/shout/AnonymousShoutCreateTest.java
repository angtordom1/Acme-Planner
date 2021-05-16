package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	//Test cases
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String author, final String text, final String info) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		
		super.checkButtonExists("Shout!");
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shouts");
		
//		final Date moment;		
//		moment = new Date(System.currentTimeMillis() - 1);

//		super.checkColumnHasValue(recordIndex, 0, moment.toString());
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String author, final String text, final String info) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		
		super.checkButtonExists("Shout!");
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
}
