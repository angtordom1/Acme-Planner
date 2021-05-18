package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanCreateTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
		
	/*
	 * TEST DE CREACIÓN POSITIVO: Este caso de prueba comprobará que la funcionalidad de alta de planes de trabajo funciona correctamente al pasarle parámetros correctos.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "workplans" deben ser creadas sin errores.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String periodStart, final String periodEnd, final String workload, final String state, final String tasks, final String finished) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create WorkPlan");
		
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("state", state);
//		super.fillInputBoxIn("tasks", tasks); // ver si se pueden meter varias entradas, pero funciona con el select
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Manager", "My workplans");
		
		super.checkColumnHasValue(recordIndex, 0, periodStart);
		super.checkColumnHasValue(recordIndex, 1, periodEnd);
		super.checkColumnHasValue(recordIndex, 2 , workload);
		
		super.clickOnListingRecord(recordIndex);
	
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("state", state);
		super.checkInputBoxHasValue("finished", finished);
		
		super.signOut();
	}
	
	/*
	 * TEST DE CREACIÓN NEGATIVO: Este caso de prueba comprobará que la funcionalidad de alta de planes de trabajo funciona correctamente al pasarle parámetros incorrectos.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "workplans" no deben ser creadas, ya que les estamos pasando parámetros que incumplen diferentes 
	 * 					   restricciones (campos vacíos, fechas incorrectas).
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final String periodStart, final String periodEnd, final String tasks, final String state) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create WorkPlan");
		
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("state", state);
//		super.fillInputBoxIn("tasks", tasks); // ver si se pueden meter varias entradas, pero funciona con el select
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}
