package br.com.henrique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.henrique.model.Estado;
import br.com.henrique.repository.EstadoRepository;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repositEstado;
    
    // Lista Estado
    public List<Estado> findAll() {
        List<Estado> estados = new ArrayList<Estado>();
        estados = repositEstado.findAll();
        
        return estados;
    }    
    
    // Lista Estados com Paginação
    public Page<Estado> findAllPage(Pageable pageable) {
        return repositEstado.findAll(pageable);
    }
 
    // Busca por Estado
    public Estado findById(String sigla) {
        Estado estado = repositEstado.findById(sigla).orElse(null);
        if (estado == null) {
            throw new ObjectNotFoundException("Estado nao encontrado !");
        }        
        return estado;
    }
    
    // Inclui Empresa
    public Estado addEstado(Estado estado) {
        Estado estadoBuscaID = repositEstado.findById(estado.getSigla()).orElse(null);
        if (estadoBuscaID != null) {
            throw new ObjectFoundException("Estado já cadastrado !");
        }            	
        return repositEstado.save(estado);
    }
    
    // Altera Estado
    public void updateEstado(String sigla, Estado estado) {
        Estado estadoAtualizado = this.findById(sigla);
        if (estadoAtualizado == null) {
            throw new ObjectNotFoundException("Estado nao encontrado !");
        }                
        estadoAtualizado.setNome(estado.getNome());
        
        repositEstado.save(estadoAtualizado);
    }
    
    // Excluir Estado
    public void deletaEstado(String sigla) {
        Estado estadoExcluir = this.findById(sigla);
        if (estadoExcluir == null) {
            throw new ObjectNotFoundException("Estado nao encontrado !");
        }     
        
        repositEstado.deleteById(sigla);
    }
}
