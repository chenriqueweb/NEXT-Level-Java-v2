package br.com.henrique;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.henrique.model.Estado;
import br.com.henrique.repository.EstadoRepository;

class EstadoServiceTest {
	
    @Autowired
    private EstadoRepository repositEstado;

	@Test
	void testFindAll() {
        List<Estado> estados = new ArrayList<Estado>();
        estados = repositEstado.findAll();
        
        if(estados.isEmpty()) {
        	fail("Falha na lista de Estados");
        }
	}

	@Test
	@Disabled
	void testFindAllPage() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testAddEstado() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testUpdateEstado() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testDeletaEstado() {
		fail("Not yet implemented");
	}

}
