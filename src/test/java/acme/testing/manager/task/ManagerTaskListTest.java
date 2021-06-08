package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskListTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	/*
	 * TEST DE LISTADO: Este caso de prueba comprobará que la funcionalidad de listado de tareas funciona correctamente.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "tasks" deben ser recorridas y consultadas sin errores al comprobar sus campos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String title, final String periodStart, final String periodEnd, final String workload, final String description, final String link, final String state, final String finished) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, periodStart);
		super.checkColumnHasValue(recordIndex, 2, periodEnd);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("state", state);
		super.checkInputBoxHasValue("finished", finished);
		
		super.signOut();
	}

}
