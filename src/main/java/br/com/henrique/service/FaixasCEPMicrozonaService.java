package br.com.henrique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.FaixasCEPMicrozonaPK;
import br.com.henrique.repository.FaixasCEPMicrozonaRepository;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class FaixasCEPMicrozonaService {

    @Autowired
    private FaixasCEPMicrozonaRepository repositFaixasCEPMicrozona;    
    
    // Lista Faixas de CEPs da Microzona
    public List<FaixasCEPMicrozona> findAll() {
        List<FaixasCEPMicrozona> faixasCEPMicrozona = new ArrayList<FaixasCEPMicrozona>();
        faixasCEPMicrozona = repositFaixasCEPMicrozona.findAll();        
        return faixasCEPMicrozona;
    }
    
    
    // Busca por Faixas de CEP da Microzona
    public FaixasCEPMicrozona findById(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK) {
        FaixasCEPMicrozona faixasCEPMicrozonaBusca2 = repositFaixasCEPMicrozona.findById(faixasCEPMicrozonaPK).orElse(null);
        
        if (faixasCEPMicrozonaBusca2 == null) {
            throw new ObjectNotFoundException("Faixa de CEP nao encontrada !");
        }
        return faixasCEPMicrozonaBusca2;
    }    
    
    
    // Inclui Faixas de CEP da Microzona
    public FaixasCEPMicrozona addFaixasCEPMicrozona(FaixasCEPMicrozona faixasCEPMicrozona) {
            return repositFaixasCEPMicrozona.save(faixasCEPMicrozona);
    }

    
    // Atualiza Faixas de CEP da Microzona
    public void updateFaixasCEPMicrozona(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK, 
                                         FaixasCEPMicrozona faixasCEPMicrozona) {
        FaixasCEPMicrozona faixasCEPMicrozonaAtualizado = findById(faixasCEPMicrozonaPK);   //  repositFaixasCEPMicrozona.findById(faixasCEPMicrozonaPK).orElse(null);
        
        faixasCEPMicrozonaAtualizado.setCEPinicial(faixasCEPMicrozona.getCEPinicial());
        faixasCEPMicrozonaAtualizado.setCEPfinal(faixasCEPMicrozona.getCEPfinal());
        
        repositFaixasCEPMicrozona.save(faixasCEPMicrozonaAtualizado);
    }
    
   
    // Exclusão da Faixa de CEP da Microzona
    public void deletaFaixasCEPMicrozona(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK) {
        this.findById(faixasCEPMicrozonaPK);

        repositFaixasCEPMicrozona.deleteById(faixasCEPMicrozonaPK);
    }
}
