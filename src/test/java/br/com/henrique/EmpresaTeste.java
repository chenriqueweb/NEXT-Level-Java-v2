package br.com.henrique;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.henrique.repository.EmpresaRepository;
import br.com.henrique.service.EmpresaService;

class EmpresaTeste {
	
    @Autowired
    private EmpresaRepository repositEmpresa;

	@Test
	void empresaTestefindById() {
		EmpresaService.class.getAnnotatedInterfaces();
		
		// fail("Not yet implemented");
		Integer codigoEsperado = 21;
		Integer codigoRetornado = repositEmpresa.findById(codigoEsperado).get().getCodigo();
		
		assertEquals(codigoEsperado, codigoRetornado, 0);
	}

}
