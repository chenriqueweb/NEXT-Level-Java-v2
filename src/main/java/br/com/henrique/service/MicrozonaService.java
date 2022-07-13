package br.com.henrique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.model.Microzona;
import br.com.henrique.repository.MicrozonaRepository;
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
    
    // Inclui Microzona
    public Microzona addMicrozona(Microzona microzona) {
        return repositMicrozona.save(microzona);
    }    
    
    // Busca pela Microzona
    public Microzona findById(Integer codigo) {
        Microzona microzona = repositMicrozona.findById(codigo).orElse(null);
        if (microzona == null) {
            throw new ObjectNotFoundException("Microzona nao encontrada !");
        }
        return microzona;
    }    
    
    // Atualiza uma Microzona
    public void updateMicrozona(Integer codigo, Microzona microzona) {
        Microzona microzonaAtualizado = this.findById(codigo);
        
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
        this.findById(codigo);
        
        repositMicrozona.deleteById(codigo);
    }    
}
