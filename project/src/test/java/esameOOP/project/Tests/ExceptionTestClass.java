package esameOOP.project.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import esameOOP.project.Exceptions.*;
import esameOOP.project.Filters.FilterHandler;
import esameOOP.project.Util.Operations;

class ExceptionTestClass {
	private String body;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void TokenNotFoundExceptionTest() {
		assertThrows(TokenNotFoundException.class, ()->Operations.readFromFile("non_esiste.txt"));
	}
	
	@Test
	void FilterNotFoundExceptionTest() {
		body=null;
		assertThrows(FilterNotFoundException.class, ()->new FilterHandler(body));
	}
	
	@Test
	void InvalidFilterExceptionTest() {
		body="{\"wrong field\":\"lancia tutto\"}";
		assertThrows(InvalidFilterException.class,()->new FilterHandler(body));
	}

	//Non penso di poter testare FailedConnectionException perché viene lanciata solo 
	//se ci sono problemi con la chiamata a FB,i quali non dovrebbero esserci
}
