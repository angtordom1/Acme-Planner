package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanListTest extends AcmePlannerTest{
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
		
	/*
	 * TEST DE LISTADO: Este caso de prueba comprobará que la funcionalidad de listado de planes de trabajo funciona correctamente.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "workplans" deben ser recorridas y consultadas sin errores al comprobar sus campos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String periodStart, final String periodEnd, final String workload, final String state, final String finished, final String published) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My Workplans");
		
		super.checkColumnHasValue(recordIndex, 0, periodStart);
		super.checkColumnHasValue(recordIndex, 1, periodEnd);
		super.checkColumnHasValue(recordIndex, 2, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("state", state);
		super.checkInputBoxHasValue("finished", finished);
		super.checkInputBoxHasValue("published", published);
		
		if(finished.equals("false") && published.equals("false")) {
			super.checkButtonExists("Update WorkPlan");
			super.checkButtonExists("Delete WorkPlan");
		}
		
		super.signOut();
	}
}
