package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest{
	
	//Test cases
	
	/*
	 *  Functionality to be tested: Authenticated task list 
	 *  Expected results: A list of persistent tasks where State and Finished are set to true
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String title, final String periodStart, final String periodEnd, final String workload,
		final String link, final String description, final String state, final String finished) {
		
		super.signIn("manager3", "manager3");
		super.clickOnMenu("Authenticated", "List tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, periodStart);
		super.checkColumnHasValue(recordIndex, 2, periodEnd);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("state", state);
		super.checkInputBoxHasValue("finished", finished);
	
		super.signOut();
	}
}
