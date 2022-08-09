package br.com.henrique;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.henrique.model.Empresa;
import br.com.henrique.repository.EmpresaRepository;

class old_EmpresaTeste {
	
    @Autowired
    
    private EmpresaRepository repositEmpresaTest;

	@Test
	void empresaTestefindById() {
		// EmpresaService.class.getAnnotatedInterfaces();
		
		Integer codigoEsperado = 21;
		
		// Integer codigoRetornado = repositEmpresa.findById(codigoEsperado).get().getCodigo();
		
		Empresa empresaRetornoTest = repositEmpresaTest.findById(codigoEsperado).orElse(null);
		
		Integer codigoRetornado = empresaRetornoTest.getCodigo();
		
		assertEquals(codigoEsperado, codigoRetornado, 0);
	}

}
