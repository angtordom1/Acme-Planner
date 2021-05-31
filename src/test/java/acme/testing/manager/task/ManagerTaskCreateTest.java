package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	/*
	 * TEST DE CREACIÓN POSITIVO: Este caso de prueba comprobará que la funcionalidad de alta de tareas funciona correctamente al pasarle parámetros correctos.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "tasks" deben ser creadas sin errores.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String periodStart, final String periodEnd, final String workload, final String description, final String link, final String state, final String finished) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("state", state);
		super.clickOnSubmitButton("Create");
		
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
	
	/*
	 * TEST DE CREACIÓN NEGATIVO: Este caso de prueba comprobará que la funcionalidad de alta de tareas funciona correctamente al pasarle parámetros incorrectos.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "tasks" no deben ser creadas, ya que les estamos pasando parámetros que incumplen diferentes 
	 * 					   restricciones (campos vacíos, fechas incorrectas, carga de trabajo incorrecta, sobrecarga de spam).
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final String title, final String periodStart, final String periodEnd, final String workload, final String description, final String link, final String state) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("state", state);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
