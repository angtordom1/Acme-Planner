package acme.testing.administrator.dashboard;

import org.hibernate.internal.util.StringHelper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowTest extends AcmePlannerTest {


	//Funcion para comprobar los datos en un elemento tr
	public void checkRowHasValue(final String attributeIndex, final String expectedValue) {
		assert !StringHelper.isBlank(attributeIndex);
		assert !StringHelper.isBlank(expectedValue);

		By locator;
		
		final String[] values = expectedValue.split(" ");
		final int length=values.length;
		if(length == 1) {

			locator = By.xpath(String.format("//tr[contains(., '%s')][contains(., '%s')]", attributeIndex , expectedValue));
			assert super.exists(locator) : String.format("Cannot find a row '%s' with value '%s'", attributeIndex , expectedValue);
		}else {

			for(int i=0 ; i<length ; i++) {
				final String value=values[i];
				locator = By.xpath(String.format("//tr[contains(., '%s')][contains(., '%s')]", attributeIndex,value));
				assert super.exists(locator) : String.format("Cannot find a row '%s' with value '%s'", attributeIndex,value);
			}

		}

	}

	//Se testea showService, esperamos que todos los datos se muestren correctamente
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/dashboard/show.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(10)
	public void show(final String nTaskPublic, final String nTaskPriv, final String nTaskEnd, final String nTaskNoEnd, 
		final String averPeriod, final String desvPeriod, final String minPeriod, final String maxPeriod,
		final String averLoad, final String desvLoad, final String minLoad, final String maxLoad, 
		final String nWorkPlan, final String nWorkPlanPriv, final String nWorkPlanPubl, final String nWorkPlanNoEnd, final String nWorkPlanEnd,
		final String nWorkPlanUnpublished, final String nWorkPlanPublished) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Dashboard");

		this.checkRowHasValue("Number of public tasks", nTaskPublic);
		this.checkRowHasValue("Number of private tasks", nTaskPriv);
		this.checkRowHasValue("Number of finished tasks", nTaskEnd);
		this.checkRowHasValue("Number of unfinished tasks", nTaskNoEnd);

		this.checkRowHasValue("Average period execution", averPeriod);
		this.checkRowHasValue("Deviation of period execution", desvPeriod);
		this.checkRowHasValue("Minimun period execution", minPeriod);
		this.checkRowHasValue("Maximun period execution", maxPeriod);

		this.checkRowHasValue("Average workload", averLoad);
		this.checkRowHasValue("Deviation of workload", desvLoad);
		this.checkRowHasValue("Minimun workload", minLoad);
		this.checkRowHasValue("Maximun workload", maxLoad);

		this.checkRowHasValue("Number of workplans", nWorkPlan);
		this.checkRowHasValue("Number of unpublished workplans", nWorkPlanUnpublished);
		this.checkRowHasValue("Number of published workplans", nWorkPlanPublished);
		this.checkRowHasValue("Number of unfinished workplans", nWorkPlanNoEnd);
		this.checkRowHasValue("Number of finished workplans", nWorkPlanEnd);
		this.checkRowHasValue("Number of private workplans", nWorkPlanPriv);
		this.checkRowHasValue("Number of public workplans", nWorkPlanPubl);

		super.signOut();
	}
}
