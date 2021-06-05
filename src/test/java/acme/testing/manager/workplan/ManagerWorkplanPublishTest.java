package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanPublishTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
			
	/*
	 * TEST DE PUBLICACIÓN: Este caso de prueba comprobará que la funcionalidad de publicación de planes de trabajo funciona correctamente.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "workplans" deben ser recorridas, consultadas y su estado debe pasar de privado a público sin errores.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/publish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void publish(final int recordIndex) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("published", "false");
		
		super.checkButtonExists("Publish WorkPlan");
		super.clickOnSubmitButton("Publish WorkPlan");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("published", "true");
				
		super.signOut();
	}

}
