package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanDeleteTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
		
	/*
	 * TEST DE BORRADO: Este caso de prueba comprobará que la funcionalidad de borrado de planes de trabajo funciona correctamente.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "workplans" deben ser recorridas, consultadas y borradas sin errores.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkButtonExists("Delete WorkPlan");
		super.clickOnSubmitButton("Delete WorkPlan");
				
		super.signOut();
	}
}
