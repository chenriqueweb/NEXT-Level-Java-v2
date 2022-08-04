package br.com.henrique;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.henrique.model.Estado;

class EstadoTest {

	@Test
	void test() {
		Estado estadoTest = new Estado();
		assertEquals("testado", estadoTest.getNome());
		
		// fail("Not yet implemented");
	}

}
