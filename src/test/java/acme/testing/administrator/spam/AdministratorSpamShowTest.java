package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamShowTest extends AcmePlannerTest {

	/*
	 * TEST DE CONSULTA: Este caso de prueba comprobará que la funcionalidad de consulta del umbral de spam funciona correctamente. Además comprobamos que se detecta adecuadamente el spam mediante una creación de un shout.
	 * RESULTADO ESPERADO: Se comprueba que el valor por defecto del umbral es 10.00 y que un shout con menos spam del umbral puede crearse mientras que un shout con más spam que el límite, no.
	 */
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spam/showPositive.csv",encoding="utf-8",numLinesToSkip = 1)
	@Order(31)
	public void showPositive(final String threshold, final String authorOK, final String textOK, final String infoOK, final String authorBAD, final String textBAD, final String infoBAD) {
		super.signIn("administrator", "administrator");
		
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
	

	
}
