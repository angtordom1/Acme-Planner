package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamUpdateTest extends AcmePlannerTest{
	
	/*
	 * TEST DE ACTUALIZACIÓN POSITIVO: Este caso de prueba comprobará que la funcionalidad de actualización del umbral de spam funciona correctamente. Además comprobamos que se detecta adecuadamente el nuevo umbral de spam mediante una creación de un shout.
	 * RESULTADO ESPERADO: Se comprueba que el nuevo valor del umbral se ha actualizado correctamente y que un shout con menos spam del umbral puede crearse mientras que un shout con más spam que el límite, no.
	 */
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spam/updatePositive.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final String threshold, final String authorOK, final String textOK, final String infoOK, final String authorBAD, final String textBAD, final String infoBAD) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		super.fillInputBoxIn("umbral", threshold);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		super.checkInputBoxHasValue("umbral", threshold);
		
		super.signOut();
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", authorOK);
		super.fillInputBoxIn("text", textOK);
		super.fillInputBoxIn("info", infoOK);
		
		super.checkButtonExists("Shout!");
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", authorBAD);
		super.fillInputBoxIn("text", textBAD);
		super.fillInputBoxIn("info", infoBAD);
		
		super.checkButtonExists("Shout!");
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
	
	/*
	 * TEST DE ACTUALIZACIÓN NEGATIVO: Este caso de prueba comprobará que la funcionalidad de actualización del umbral de spam funciona correctamente y detecta errores cuando se le pasa un valor de umbral no válido.
	 * RESULTADO ESPERADO: Se comprueba que el nuevo valor del umbral no se ha podido actualizar porque devuelve un error.
	 */
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spam/updateNegative.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spams parameters");
		
		super.fillInputBoxIn("umbral", threshold);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
		
	}
}
