package br.com.henrique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.henrique.model.Microzona;
import br.com.henrique.repository.MicrozonaRepository;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class MicrozonaService {
    
    @Autowired
    private MicrozonaRepository repositMicrozona;

    // Lista Microzonas
    public List<Microzona> findAll() {
        List<Microzona> microzonas = new ArrayList<Microzona>();
        microzonas = repositMicrozona.findAll();
        
        return microzonas;
    }
    
    // Lista de Microzonas com Paginação
    public Page<Microzona> findAllPage(Pageable pageable) {
        return repositMicrozona.findAll(pageable);
    }         

    // Busca pela Microzona
    public Microzona findById(Integer codigo) {
        Microzona microzona = repositMicrozona.findById(codigo).orElse(null);
        if (microzona == null) {
            throw new ObjectNotFoundException("Microzona nao encontrada !");
        }
        return microzona;
    }    
    
    // Inclui Microzona
    public Microzona addMicrozona(Microzona microzona) {
    	Integer codigoMicrozona = microzona.getCodigo();
    	if (codigoMicrozona == null) { 
    		codigoMicrozona = 0;
    	}
    	
        Microzona microzonaBuscaID = repositMicrozona.findById(codigoMicrozona).orElse(null);
        if (microzonaBuscaID != null) {
            throw new ObjectFoundException("Microzona já cadastrada !");
        }
        
        return repositMicrozona.save(microzona);
    }    
    
    // Atualiza uma Microzona
    public void updateMicrozona(Integer codigo, Microzona microzona) {
        Microzona microzonaAtualizado = this.findById(codigo);
        if (microzonaAtualizado == null) {
            throw new ObjectFoundException("Microzona nao encontrada !");
        }        
        
        microzonaAtualizado.setNome(microzona.getNome());
        microzonaAtualizado.setStatus(microzona.getStatus());
        microzonaAtualizado.setAtendimentoDiario(microzona.getAtendimentoDiario());
        
        microzonaAtualizado.setAtendeSegunda(microzona.getAtendeSegunda());
        microzonaAtualizado.setAtendeTerca(microzona.getAtendeTerca());
        microzonaAtualizado.setAtendeQuarta(microzona.getAtendeQuarta());
        microzonaAtualizado.setAtendeQuinta(microzona.getAtendeQuinta());
        microzonaAtualizado.setAtendeSexta(microzona.getAtendeSexta());
        microzonaAtualizado.setAtendeSabado(microzona.getAtendeSabado());
                
        microzonaAtualizado.setCodigoMunicipio(microzona.getCodigoMunicipio());
        microzonaAtualizado.setEstadoRota(microzona.getEstadoRota());
        microzonaAtualizado.setCodigoRota(microzona.getCodigoRota());
        
        repositMicrozona.save(microzonaAtualizado);
    }    
    
    // Exclusão da Microzona
    public void deletaMicrozona(Integer codigo) {
        Microzona microzonaExcluir = this.findById(codigo);
        if (microzonaExcluir == null) {
            throw new ObjectFoundException("Microzona nao encontrada !");
        }              
        
        repositMicrozona.deleteById(codigo);
    }    
}
