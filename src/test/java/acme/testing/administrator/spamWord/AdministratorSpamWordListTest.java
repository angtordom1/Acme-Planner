package acme.testing.administrator.spamWord;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordListTest extends AcmePlannerTest{
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamWord/list.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(31)
	public void list(final int recordIndex 	, final String word, final String size) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		By toggleLocator, headerLocator;
		WebElement toggle;
		String ariaExpanded;
		toggleLocator = By.xpath("//button[@class='default']");
		toggle = super.locateOne(toggleLocator);
		if (toggle.isDisplayed()) {
			ariaExpanded = toggle.getAttribute("aria-expanded");
			if (ariaExpanded == null)
				super.clickAndGo(toggle);
		}
		headerLocator = By.xpath(String.format("//div[@id='mainMenu']/ul/li/a[normalize-space()='%s']", "Show spam words"));
		super.clickAndWait(headerLocator);
			
		super.clickOnMenu("Show spam words",null);
		
		super.checkColumnHasValue(recordIndex, 0, "word");
		super.checkColumnHasValue(recordIndex, 1, "size");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);
		super.checkInputBoxHasValue("size", size);
		
		super.signOut();
		
	}
}
