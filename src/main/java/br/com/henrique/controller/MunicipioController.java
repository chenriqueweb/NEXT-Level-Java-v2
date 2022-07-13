package br.com.henrique.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.henrique.model.Municipio;
import br.com.henrique.service.MunicipioService;

@RestController
@RequestMapping(path = "/municipio")
public class MunicipioController {
    
    @Autowired   
    private MunicipioService municipioService;
    
    // Lista Municipio
    @GetMapping
    public ResponseEntity<List<Municipio>> findAll() {
        List<Municipio> municipios = municipioService.findAll();        
        return ResponseEntity.ok().body(municipios);
    }
   
    // Busca pelo Municipio
    @GetMapping(path = "{codigo}")
    public ResponseEntity<Municipio> findById(@PathVariable Integer codigo) {
        Municipio municipio = municipioService.findById(codigo);
        return ResponseEntity.ok().body(municipio);
    }    

    // Inclui Municipio
    @PostMapping
    public ResponseEntity<Void> addMunicipio(@RequestBody Municipio municipio) {
        Municipio municipioNovo = municipioService.addMunicipio(municipio);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(municipioNovo.getCodigo_ID()).toUri();
        return ResponseEntity.created(uri).build();
    }    
    
    // Altera Municipio
    @PutMapping(path = "{codigo}")
    public ResponseEntity<Void> updateMunicipio(@PathVariable Integer codigo, @RequestBody Municipio municipio) {
        municipioService.updateMunicipio(codigo, municipio);
        return ResponseEntity.noContent().build();
    }
    
    // Deleta Municipio = method Delete
    @DeleteMapping(path = "{codigo}")
    public ResponseEntity<Void> deletaMunicipio(@PathVariable Integer codigo) {
        municipioService.deletaMunicipio(codigo);
        return ResponseEntity.noContent().build();
    }
    
    //----------------------------------------------------------------------------------------
    // Altera Municipio
    // method Post (página)
    @GetMapping(path = "/editar/{codigo}")
    public ModelAndView editarMunicipioWeb(@PathVariable Integer codigo) {
        ModelAndView modelAndView = new ModelAndView("MunicipioFormulario");
        
        Municipio municipio = municipioService.findById(codigo);
        
        modelAndView.addObject("municipio", municipio);
        
        return modelAndView;
    }        

    
    // Deleta estado e chama Lista de Municipio
    // method Post (página)
//    @PostMapping(path = "/remover/{codigo}")
//    public ModelAndView deletaMunicipioWeb(@PathVariable Integer codigo) {
//        municipioService.deletaMunicipio(codigo);
//        
//        List<Municipio> municipios = municipioService.findAll();
//        
//        ModelAndView modelAndView = new ModelAndView("MunicipioListar");
//        modelAndView.addObject("municipios", municipios);
//        
//        return modelAndView;
//    } 
//    
}
