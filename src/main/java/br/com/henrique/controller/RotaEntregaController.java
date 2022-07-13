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

import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.service.RotaEntregaService;

@RestController
@RequestMapping(path = "/rotaEntrega")
public class RotaEntregaController {
    
    @Autowired
    private RotaEntregaService rotaEntregaService;

    // Lista RotaEntrega
    @GetMapping
    public ResponseEntity<List<RotaEntrega>> findAll() {
        List<RotaEntrega> rotaEntregas = rotaEntregaService.findAll();
        return ResponseEntity.ok().body(rotaEntregas);
    }
    
    // Busca por RotaEntrega
    @GetMapping(path = "{siglaEstado}/{codigoRota}")
    public ResponseEntity<RotaEntrega> findById(@PathVariable String siglaEstado,
                                                @PathVariable Integer codigoRota) {
        RotaEntregaPK rotaEntregaPK = new RotaEntregaPK();
        rotaEntregaPK.setSiglaEstado(siglaEstado);
        rotaEntregaPK.setCodigoRota(codigoRota);
        
        RotaEntrega rotaEntregaBusca = rotaEntregaService.findById(rotaEntregaPK);
        
        return ResponseEntity.ok().body(rotaEntregaBusca);
    }
    
    // Inclui RotaEntrega
    @PostMapping
    public ResponseEntity<Void> addRotaEntrega(@RequestBody RotaEntrega rotaEntrega) {
        RotaEntrega rotaEntregaNova = rotaEntregaService.addRotaEntrega(rotaEntrega);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{siglaEstado}/{codigoRota}")
                  .buildAndExpand(rotaEntregaNova.getRotaEntregaPK().getSiglaEstado(),
                                  rotaEntregaNova.getRotaEntregaPK().getCodigoRota())
                  .toUri();
        return ResponseEntity.created(uri).build();
    }
    
    // Altera RotaEntrega
    @PutMapping(path = "{siglaEstado}/{codigoRota}")
    public ResponseEntity<Void> updateRotaEntrega(@PathVariable String siglaEstado,
                                                  @PathVariable Integer codigoRota, 
                                                  @RequestBody RotaEntrega rotaEntrega) {
        
        RotaEntregaPK rotaEntregaPK = new RotaEntregaPK();
        rotaEntregaPK.setSiglaEstado(siglaEstado);
        rotaEntregaPK.setCodigoRota(codigoRota);
    
        rotaEntregaService.updateRotaEntrega(rotaEntregaPK, rotaEntrega);
        
        return ResponseEntity.noContent().build();
    }
    
    // Exclusão RotaEntrega
    @DeleteMapping(path = "{siglaEstado}/{codigoRota}")
    public ResponseEntity<Void> deletaRotaEntrega(@PathVariable String siglaEstado,
                                                  @PathVariable Integer codigoRota) {
        
        RotaEntregaPK rotaEntregaPK = new RotaEntregaPK();
        rotaEntregaPK.setSiglaEstado(siglaEstado);
        rotaEntregaPK.setCodigoRota(codigoRota);
        
        rotaEntregaService.deletaRotaEntrega(rotaEntregaPK);
        return ResponseEntity.noContent().build();
    }
    
//    //-----------------------------------------------------------------------------------------------------
//    // Exclui rotaEntrega e chama Lista de RotaEntregas
//    // method Post (página)
    @PostMapping(path = "/remover/{siglaEstado}/{codigoRota}")
    public ModelAndView deletaRotaEntregaWeb(@PathVariable String siglaEstado, 
                                             @PathVariable Integer codigoRota,
                                             @RequestBody RotaEntrega rotaEntrega) {
        rotaEntregaService.deletaRotaEntrega(rotaEntrega.getRotaEntregaPK());
        
        List<RotaEntrega> rotaEntregas = rotaEntregaService.findAll();
        
        ModelAndView modelAndView = new ModelAndView("RotaEntregaListar");
        modelAndView.addObject("rotaEntregas", rotaEntregas);
        
        return modelAndView;
    }         
    
    // Altera rotaEntrega
    // method Post (página)
    @GetMapping(path = "/editar/{siglaEstado}/{codigoRota}")
    public ModelAndView editarRotaEntregaWeb(@PathVariable String siglaEstado,
                                             @PathVariable Integer codigoRota,
                                             @RequestBody RotaEntrega rotaEntrega) {
        ModelAndView modelAndView = new ModelAndView("RotaEntregaFormulario");
        
        RotaEntrega rotaEntregaAltera = rotaEntregaService.findById(rotaEntrega.getRotaEntregaPK());
        
        modelAndView.addObject("rotaEntrega", rotaEntregaAltera);
        
        return modelAndView;
    }
}