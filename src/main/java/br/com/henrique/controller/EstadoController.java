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

import br.com.henrique.model.Estado;
import br.com.henrique.service.EstadoService;

@RestController
@RequestMapping(path = "/estado")
public class EstadoController {
    
    @Autowired
    private EstadoService estadoService;
    
    // Lista Estado
    @GetMapping
    public ResponseEntity<List<Estado>> findAll() {
        List<Estado> estados = estadoService.findAll();
        return ResponseEntity.ok().body(estados);
    }
    
    // Busca pelo Estado
    @GetMapping(path = "{sigla}")
    public ResponseEntity<Estado> findById(@PathVariable String sigla) {
        Estado estado = estadoService.findById(sigla);
        return ResponseEntity.ok().body(estado);
    }
    
    // Inclui Estado
    @PostMapping
    public ResponseEntity<Void> addEstado(@RequestBody Estado estado) {
        Estado estadoNovo = estadoService.addEstado(estado);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sigla}").buildAndExpand(estadoNovo.getSigla()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    // Altera Estado
    @PutMapping(path = "{sigla}")
    public ResponseEntity<Void> updateEstado(@PathVariable String sigla, @RequestBody Estado estado ) {
        estadoService.updateEstado(sigla, estado);
        return ResponseEntity.noContent().build();
    }

    // Exclui Estado
    @DeleteMapping(path = "{sigla}")
    public ResponseEntity<Void> deletaEstado(@PathVariable String sigla) {
        estadoService.deletaEstado(sigla);
        
        return ResponseEntity.noContent().build();
    }
    
    //----------------------------------------------------------------------------------------
    // Deleta estado e chama Lista de Estados
    // method Post (página)
    @PostMapping(path = "/remover/{sigla}")
    public ModelAndView deletaEstadoWeb(@PathVariable String sigla) {
        estadoService.deletaEstado(sigla);
        
        List<Estado> estados = estadoService.findAll();
        
        ModelAndView modelAndView = new ModelAndView("EstadoListar");
        modelAndView.addObject("estados", estados);
        
        return modelAndView;
    }     
    
    // Altera Estado
    // method Post (página)
    @GetMapping(path = "/editar/{sigla}")
    public ModelAndView editarEstadoWeb(@PathVariable String sigla) {
        ModelAndView modelAndView = new ModelAndView("EstadoFormulario");
        
        Estado estado = estadoService.findById(sigla);
        
        modelAndView.addObject("estado", estado);
        
        return modelAndView;
    }    
    
}
