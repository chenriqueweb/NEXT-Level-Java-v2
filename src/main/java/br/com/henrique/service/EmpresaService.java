package br.com.henrique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.model.Empresa;
import br.com.henrique.repository.EmpresaRepository;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository repositEmpresa;

    // Lista Empresas
    public List<Empresa> findAll() {
        List<Empresa> empresas = new ArrayList<Empresa>();
        empresas = repositEmpresa.findAll();
        
        return empresas;
    }

    // Busca pela Empresa
    public Empresa findById(Integer codigo) {
        Empresa empresa = repositEmpresa.findById(codigo).orElse(null);
        if (empresa == null) {
            throw new ObjectNotFoundException("Empresa nao encontrada !");
        }      
        return empresa;
    }

    // Inclui Empresa
    public Empresa addEmpresa(Empresa empresa) {
        return repositEmpresa.save(empresa);
    }

    // Atualiza uma Empresa
    public void updateEmpresa(Integer codigo, Empresa empresa) {
        Empresa empresaAtualizado = this.findById(codigo);
        
        empresaAtualizado.setRazaoSocial(empresa.getRazaoSocial());
        empresaAtualizado.setRaizCNPJ(empresa.getRaizCNPJ());
        empresaAtualizado.setDataAbertura(empresa.getDataAbertura());
        
        repositEmpresa.save(empresaAtualizado);
    }

    // Exclusão de Empresa
    public void deletaEmpresa(Integer codigo) {
        this.findById(codigo);

        repositEmpresa.deleteById(codigo);
    }
    
}
