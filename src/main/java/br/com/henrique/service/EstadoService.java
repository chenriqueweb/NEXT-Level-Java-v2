package br.com.henrique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.model.Estado;
import br.com.henrique.repository.EstadoRepository;
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
        return repositEstado.save(estado);
    }
    
    // Altera Estado
    public void updateEstado(String sigla, Estado estado) {
        Estado estadoAtualizado = this.findById(sigla);
        estadoAtualizado.setNome(estado.getNome());
        
        repositEstado.save(estadoAtualizado);
    }
    
    // Excluir Estado
    public void deletaEstado(String sigla) {
        this.findById(sigla);

        repositEstado.deleteById(sigla);
    }
}
