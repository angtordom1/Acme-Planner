package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
		
	/*
	 * TEST DE BORRADO: Este caso de prueba comprobará que la funcionalidad de borrado de tareas funciona correctamente.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "tasks" deben ser recorridas, consultadas y borradas sin errores.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkButtonExists("Delete");
		super.clickOnSubmitButton("Delete");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}
