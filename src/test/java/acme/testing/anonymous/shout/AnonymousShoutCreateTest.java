package acme.testing.anonymous.shout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	//Test cases
	
	/*
 	 * Esta función prueba la creación de shouts de Anonymous con valores válidos, comprobamos que 
	 * el botón de creación existe y comprobamos que los Shouts se crean correctamente volviendo al listado
	 * de shouts de Anonymous y comprobando los valores de cada columna para los shouts creados
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String author, final String text, final String info) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		
		super.checkButtonExists("Shout!");
		
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shouts");
		
		final Date moment;		
		final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");  
		moment = new Date(System.currentTimeMillis() - 1);
		super.checkColumnHasValue(recordIndex, 0, dateFormat.format(moment));
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
	}
	
	
	/*
	 * Esta función prueba la creación de shouts de Anonymous con valores inválidos, comprobamos que 
	 * el botón de creación existe y comprobamos que se dan los correspondientes errores:
	 * 		- Campo autor vacío
	 * 		- Campo text vacío
	 * 		- Campo autor con menos de 5 carácteres y con más de 25 carácteres
	 * 		- Campo text con más de 100 carácteres
	 * 		- La entidad shout está considerada como Spam
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String author, final String text, final String info) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		
		super.checkButtonExists("Shout!");
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
}
