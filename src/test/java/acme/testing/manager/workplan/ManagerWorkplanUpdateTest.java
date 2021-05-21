package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanUpdateTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	/*
	 * TEST DE ACTUALIZACIÓN POSITIVO: Este caso de prueba comprobará que la funcionalidad de actualización de planes de trabajo funciona correctamente al pasarle parámetros correctos.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "workplans" deben ser actualizadas sin errores.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String periodStart, final String periodEnd, final String workload, final String state, final String tasks,final String finished) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("workload", workload);
		if(state.equals("true")) super.fillInputBoxIn("state", "true");
		if(!(tasks == null)) super.fillInputBoxIn("tasks", tasks);
		super.fillInputBoxIn("finished", finished);
		
		super.checkButtonExists("Update WorkPlan");
		super.clickOnSubmitButton("Update WorkPlan");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("state", state);
		super.checkInputBoxHasValue("finished", finished);
		
		super.signOut();
	}
	
	/*
	 * TEST DE ACTUALIZACIÓN NEGATIVO: Este caso de prueba comprobará que la funcionalidad de actualización de planes de trabajo funciona correctamente al pasarle parámetros incorrectos.
	 * RESULTADO ESPERADO: Las entradas de la tabla de "workplans" no serán actualizadas al pasar parámetros que incumplen diferentes 
	 * 					   restricciones (campos vacíos, fechas incorrectas).
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final int recordIndex, final String periodStart, final String periodEnd, final String workload, final String state, final String tasks, final String finished) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My Workplans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.fillInputBoxIn("workload", workload);
		if(state.equals("true")) super.fillInputBoxIn("state", "true");
		if(!(tasks == null)) super.fillInputBoxIn("tasks", tasks);
		super.fillInputBoxIn("finished", finished);
		
		super.checkButtonExists("Update WorkPlan");
		super.clickOnSubmitButton("Update WorkPlan");
		
		super.checkErrorsExist();
		
		super.signOut();
	}	

}
