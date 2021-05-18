package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowTest extends AcmePlannerTest {


	@ParameterizedTest
	@CsvFileSource(resources="/administrator/dashboard/show.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(5)
	public void show(final int recordIndex, 
		final String nTaskPublic, final String nTaskPriv, final String nTaskEnd, final String nTaskNoEnd, 
		final String averPeriod, final String desvPeriod, final String minPeriod, final String maxPeriod,
		final String averLoad, final String desvLoad, final String minLoad, final String maxLoad, 
		final String nWorkPlan, final String nWorkPlanPriv, final String nWorkPlanPubl, final String nWorkPlanNoEnd, final String nWorkPlanEnd
		) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Dashboard");
		
		super.checkInputBoxHasValue("Number of public tasks" , nTaskPublic);
		super.checkInputBoxHasValue("Number of private tasks", nTaskPriv);
		super.checkInputBoxHasValue("Number of finished tasks", nTaskEnd);
		super.checkInputBoxHasValue("Number of unfinished tasks", nTaskNoEnd);
		
		super.checkInputBoxHasValue("Average period execution", averPeriod);
		super.checkInputBoxHasValue("Deviation of period execution", desvPeriod);
		super.checkInputBoxHasValue("Minimun period execution", minPeriod);
		super.checkInputBoxHasValue("Maximun period execution", maxPeriod);
		
		super.checkInputBoxHasValue("Average workload", averLoad);
		super.checkInputBoxHasValue("Deviation of workload", desvLoad);
		super.checkInputBoxHasValue("Minimun workload", minLoad);
		super.checkInputBoxHasValue("Maximun workload", maxLoad);
		
		super.checkInputBoxHasValue("Number of workplans", nWorkPlan);
		super.checkInputBoxHasValue("Number of private workplans", nWorkPlanPriv);
		super.checkInputBoxHasValue("Number of public workplans", nWorkPlanPubl);
		super.checkInputBoxHasValue("Number of unfinished workplans", nWorkPlanNoEnd);
		super.checkInputBoxHasValue("Number of finished workplans", nWorkPlanEnd);
		
		super.signOut();
	}
}
