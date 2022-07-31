package br.com.henrique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.henrique.model.Empresa;
import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.repository.EmpresaRepository;
import br.com.henrique.repository.FilialRepository;
import br.com.henrique.repository.RotaEntregaRepository;
import br.com.henrique.service.exception.ObjectFoundException;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class RotaEntregaService {

    @Autowired
    private RotaEntregaRepository repositRotaEntrega;

    @Autowired
    private EmpresaRepository repositEmpresa;
    
    @Autowired
    private FilialRepository repositFilial;
    
    // Lista de Rotas de Entrega
    public List<RotaEntrega> findAll() {
        List<RotaEntrega> rotasEntregas = new ArrayList<RotaEntrega>();
        rotasEntregas = repositRotaEntrega.findAll();        
        return rotasEntregas;
    }    
    
    // Lista de Rotas de Entrega com Paginação
    public Page<RotaEntrega> findAllPage(Pageable pageable) {
        return repositRotaEntrega.findAll(pageable);
    }        
    
    // Busca pela Rota de Entrega
    public RotaEntrega findById(RotaEntregaPK rotaEntregaPK) {
        RotaEntrega rotaEntregaBusca = repositRotaEntrega.findById(rotaEntregaPK).orElse(null);
        if (rotaEntregaBusca == null) {
            throw new ObjectNotFoundException("Rota de Entrega nao encontrada !");
        }
        
        return rotaEntregaBusca;
    }
    
    // Inclui Rota de Entrega
    public RotaEntrega addRotaEntrega(RotaEntrega rotaEntrega) {
        RotaEntrega rotaEntregaBuscaID = repositRotaEntrega.findById(rotaEntrega.getRotaEntregaPK()).orElse(null);
        if (rotaEntregaBuscaID != null) {
            throw new ObjectFoundException("Rota de Entrega já cadastrada !");
        }    	
        
        return repositRotaEntrega.save(rotaEntrega);
    }    
    
    // Atualiza uma Rota de Entrega
    public void updateRotaEntrega(RotaEntregaPK rotaEntregaPK,
                                  RotaEntrega rotaEntrega) {
        RotaEntrega rotaEntregaAtualizado = this.findById(rotaEntregaPK);
        if (rotaEntregaAtualizado == null) {
            throw new ObjectNotFoundException("Rota de Entrega nao encontrada !");
        }  
        
        Empresa empresaBuscaID = repositEmpresa.findById(rotaEntrega.getCodigoEmpresa()).orElse(null);
        if (empresaBuscaID == null) {
            throw new ObjectNotFoundException("Empresa nao encontrada !");
        }        
        
        FilialPK filialPK = new FilialPK();
        filialPK.setCodigoEmpresa(rotaEntrega.getCodigoEmpresa());
        filialPK.setCodigoFilial(rotaEntrega.getCodigoFilial());
        
        Filial filialBuscaID = repositFilial.findById(filialPK).orElse(null);
        if (filialBuscaID == null) {
            throw new ObjectNotFoundException("Filial nao encontrada !");
        }        
        
        rotaEntregaAtualizado.setNome(rotaEntrega.getNome());
        rotaEntregaAtualizado.setStatus(rotaEntrega.getStatus());
        rotaEntregaAtualizado.setCodigoEmpresa(rotaEntrega.getCodigoEmpresa());
        rotaEntregaAtualizado.setCodigoFilial(rotaEntrega.getCodigoFilial());
        rotaEntregaAtualizado.setPrazoExpedicao(rotaEntrega.getPrazoExpedicao());
        
        repositRotaEntrega.save(rotaEntregaAtualizado);
    }
    
    // Exclusão da Rota de Entrega
    public void deletaRotaEntrega(RotaEntregaPK rotaEntregaPK) {
        RotaEntrega rotaEntregaExcluir = this.findById(rotaEntregaPK);
        if (rotaEntregaExcluir == null) {
            throw new ObjectNotFoundException("Rota de Entrega nao encontrada !");
        }    	        
        
        repositRotaEntrega.deleteById(rotaEntregaPK);
    }    
    
}
