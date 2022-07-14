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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.henrique.model.Microzona;
import br.com.henrique.service.MicrozonaService;

@RestController
@RequestMapping(path = "/microzona")
public class MicrozonaController {
//
//    @PersistenceContext
//    private EntityManager manager;
//    
//    @GetMapping
//    public List<Microzona> listaMicrozona() {
//        return manager.createQuery("from Microzona", Microzona.class).getResultList();
//    }
    
    @Autowired
    private MicrozonaService microzonaService;

    // Lista Microzona
    @GetMapping
    public ResponseEntity<List<Microzona>> findAll() {
        List<Microzona> microzonas = microzonaService.findAll();
        return ResponseEntity.ok().body(microzonas);
    }
    
    // Busca por Microzona
    @GetMapping(path = "{codigo}")
    public ResponseEntity<Microzona> findById(@PathVariable Integer codigo) {
        Microzona microzona = microzonaService.findById(codigo);
        return ResponseEntity.ok().body(microzona);
    }    
    
    // Inclui Micrzona
    @PostMapping
    public ResponseEntity<Void> addMicrozona(@RequestBody Microzona microzona) {
        Microzona microzonaNova = microzonaService.addMicrozona(microzona);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(microzonaNova.getCodigo()).toUri();
        return ResponseEntity.created(uri).build();
    }        

    // Altera Microzona
    @PutMapping(path = "{codigo}")
    public ResponseEntity<Void> updateMicrozona(@PathVariable Integer codigo, 
                                                @RequestBody Microzona microzona) {
        microzonaService.updateMicrozona(codigo, microzona);
        return ResponseEntity.noContent().build();
    }    
        
    // Exclusão Microzona
    @DeleteMapping(path = "{codigo}")
    public ResponseEntity<Void> deletaMicrozona(@PathVariable Integer codigo) {
        microzonaService.deletaMicrozona(codigo);
        return ResponseEntity.noContent().build();
    }    
    
    //-----------------------------------------------------------------------------------------------------
    // Exclui empresa e chama Lista de Empresas
    // method Post (página)
//    @PostMapping(path = "/remover/{codigo}")
//    public ModelAndView deletaMicrozonaWeb(@PathVariable Integer codigo) {
//        microzonaService.deletaMicrozona(codigo);
//        
//        List<Microzona> microzonas = microzonaService.findAll();
//        
//        ModelAndView modelAndView = new ModelAndView("MicrozonaListar");
//        modelAndView.addObject("microzonas", microzonas);
//        
//        return modelAndView;
//    }         
//    
//    // Altera empresa
//    // method Post (página)
//    @GetMapping(path = "/editar/{codigo}")
//    public ModelAndView editarMicrozonaWeb(@PathVariable Integer codigo) {
//        ModelAndView modelAndView = new ModelAndView("MicrozonaFormulario");
//        
//        Microzona microzona = microzonaService.findById(codigo);
//        
//        modelAndView.addObject("microzona", microzona);
//        
//        return modelAndView;
//    }    
}