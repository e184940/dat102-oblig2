package uke6.oppgave1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParentesSjekkerTest {

	@Test
	void korrektParenteser() {
		assertTrue(ParentesSjekker.sjekkParenteser("{[()]}"));
		assertTrue(ParentesSjekker.sjekkParenteser("class HelloWorld{"
				+ "public static void main(String args[]){"
				+ "System.out.println(\"Hello world!\");}}"));
	}
	
	@Test
	void manglerSluttParentes() {
		assertFalse(ParentesSjekker.sjekkParenteser("{[()}"));
	}
	
	@Test
	void manglerStartParentes() {
		assertFalse(ParentesSjekker.sjekkParenteser("[()]}"));
	}
	
	@Test 
	void rekkefolgeParentes() {
		assertFalse(ParentesSjekker.sjekkParenteser("{[(])}"));
	}	

}
