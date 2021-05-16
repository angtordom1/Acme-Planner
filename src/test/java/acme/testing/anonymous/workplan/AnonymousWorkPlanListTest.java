package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkPlanListTest extends AcmePlannerTest{
	
	//Test cases
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplan/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String periodStart, final String periodEnd, final String workload,
		final String state, final String finished) {
		super.clickOnMenu("Anonymous", "List work plans");
		
		super.checkColumnHasValue(recordIndex, 0, periodStart);
		super.checkColumnHasValue(recordIndex, 1, periodEnd);
		super.checkColumnHasValue(recordIndex, 2, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("state", state);
		super.checkInputBoxHasValue("finished", finished);
		
	}
}
