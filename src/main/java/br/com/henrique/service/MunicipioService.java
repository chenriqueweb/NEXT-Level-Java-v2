package br.com.henrique.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.model.Municipio;
import br.com.henrique.repository.MunicipioRepository;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class MunicipioService {
    
    @Autowired
    private MunicipioRepository repositMunicipio;
    
    // Lista Municipio
    public List<Municipio> findAll() {
        List<Municipio> municipios = new ArrayList<Municipio>();
        municipios = repositMunicipio.findAll();        
        return municipios;
    }

    // Busca pelo Municipio
    public Municipio findById(Integer codigo) {
        Municipio municipio = repositMunicipio.findById(codigo).orElse(null);
        if (municipio == null) {
            throw new ObjectNotFoundException("Municipio nao encontrado !");
        }
        return municipio;
    }
    
    // Inclui Municipio
    public Municipio addMunicipio(Municipio municipio) {
            return repositMunicipio.save(municipio);
    }
    
    // Atualiza um Municipio
    public void updateMunicipio(Integer codigo, Municipio municipio) {
        Municipio municipioAtualizado = this.findById(codigo);
        
        municipioAtualizado.setNome(municipio.getNome());
        municipioAtualizado.setEstado(municipio.getEstado());
        
        repositMunicipio.save(municipioAtualizado);
    }    
    
    // Exclusão de Municipio
    public void deletaMunicipio(Integer codigo) {
        this.findById(codigo);
        
        repositMunicipio.deleteById(codigo);
    }
}
