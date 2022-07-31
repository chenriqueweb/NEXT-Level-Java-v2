package br.com.henrique.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.FaixasCEPMicrozonaPK;

@Repository
public interface FaixasCEPMicrozonaRepository extends JpaRepository<FaixasCEPMicrozona, FaixasCEPMicrozonaPK> {

	// Exemplo com JPA - JPQL
	@Query(value = "SELECT f FROM FaixasCEPMicrozona f WHERE ?1 BETWEEN CEPinicial AND CEPfinal")
	public List<FaixasCEPMicrozona> procuraPorFaixa(Integer cep);
	
	public List<FaixasCEPMicrozona> findByCEPinicialGreaterThanEqualAndCEPfinalLessThanEqual(Integer cep1, Integer cep2);
}
