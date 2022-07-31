package br.com.henrique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.repository.FilialRepository;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class FilialService {

    @Autowired
    private FilialRepository repositFilial;
    
    // Lista Filiais
    public List<Filial> findAll() {
        List<Filial> filiais = new ArrayList<Filial>();
        filiais = repositFilial.findAll();                
        return filiais;
    }
    
    // Lista de Filiais com Paginação
    public Page<Filial> findAllPage(Pageable pageable) {
        return repositFilial.findAll(pageable);
    }    
    
    // Busca por Filial
    public Filial findById(FilialPK filialPK) {
        Filial filialBusca = repositFilial.findById(filialPK).orElse(null);
        if (filialBusca == null) {
            throw new ObjectNotFoundException("Filial nao encontrada !");
        }
        return filialBusca;
    }
    
    // Inclui Filial
    public Filial addFilial(Filial filial) {
        Filial filialBuscaID = repositFilial.findById(filial.getFilialPK()).orElse(null);
        if (filialBuscaID != null) {
            throw new ObjectFoundException("Filial já encontrada !");
        } 	
        
        Filial filialBuscaCNPJ= repositFilial.findByCNPJ(filial.getCnpj());
        if (filialBuscaCNPJ != null) {
            throw new ObjectFoundException("CNPJ informado já encontra-se cadastrado para outra Filial !");
        } 	        
        return repositFilial.save(filial);
    }
    
    // Atualiza Filial
    public void updateFilial(FilialPK filialPK, 
                             Filial filial) {
        Filial filialAtualizado = this.findById(filial.getFilialPK());
        if (filialAtualizado != null) {
            throw new ObjectNotFoundException("Filial nao encontrada !");
        } 	        
        
        filialAtualizado.setNome(filial.getNome());
        filialAtualizado.setCnpj(filial.getCnpj());
        filialAtualizado.setMunicipio(filial.getMunicipio());
        
        repositFilial.save(filialAtualizado);
    }
    
    // Exclusão de Filial
    public void deletaFilial(FilialPK filialPK) {
        Filial filialExcluir = this.findById(filialPK);
        if (filialExcluir != null) {
            throw new ObjectNotFoundException("Filial nao encontrada !");
        } 	   
        
        repositFilial.deleteById(filialPK);
    }

}
