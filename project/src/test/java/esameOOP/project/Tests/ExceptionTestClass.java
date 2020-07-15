package esameOOP.project.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import esameOOP.project.Exceptions.*;
import esameOOP.project.Filters.FilterHandler;
import esameOOP.project.Util.Operations;

/**
 * Questa classe di test effettua test sulle eccezioni personalizzate
 * dell'applicazione
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
class ExceptionTestClass {
	private String body;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Questo test controlla che venga lanciata l'eccezione
	 * {@link TokenNotFoundException} se viene passato un file inesistente a
	 * {@link Operations#readFromFile(String)}
	 */
	@Test
	void TokenNotFoundExceptionTest() {
		assertThrows(TokenNotFoundException.class, () -> Operations.readFromFile("non_esiste.txt"));
	}

	/**
	 * Questo test controlla che venga lanciata l'eccezione
	 * {@link FilterNotFoundException} se si passa un body nullo al costruttore
	 * della classe {@link FilterHandler}
	 */
	@Test
	void FilterNotFoundExceptionTest() {
		body = null;
		assertThrows(FilterNotFoundException.class, () -> new FilterHandler(body));
	}

	/**
	 * Questo test controlla che venga lanciata l'eccezione
	 * {@link InvalidFilterException} se si scrive il body della richiesta male
	 */
	@Test
	void InvalidFilterExceptionTest() {
		body = "{\"wrong field\":\"lancia tutto\"}";
		assertThrows(InvalidFilterException.class, () -> new FilterHandler(body));
		body = "{\"created_time\":{\"after\":\"202/01/01\"}}";
		assertThrows(InvalidFilterException.class, () -> new FilterHandler(body));
		body = "[{\"created_time\":{\"after\":\"2020/01/01\"}},AN,{\"type\":\"photo\"}]";
		assertThrows(InvalidFilterException.class, () -> new FilterHandler(body));
	}

	// Non penso di poter testare FailedConnectionException perché viene lanciata
	// solo
	// se ci sono problemi con la chiamata a FB,i quali non dovrebbero esserci
	// Allo stesso modo non posso testare EmptyFeedException, perché viene lanciata
	// solo da metodi
	// del controller
}
