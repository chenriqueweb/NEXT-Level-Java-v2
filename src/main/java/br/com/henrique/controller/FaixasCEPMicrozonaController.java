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

import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.FaixasCEPMicrozonaPK;
import br.com.henrique.service.FaixasCEPMicrozonaService;

@RestController
@RequestMapping(path = "/faixasCEPMicrozona")
public class FaixasCEPMicrozonaController {
    
    @Autowired
    private FaixasCEPMicrozonaService faixasCEPMicrozonaService;

    // Lista Faixas de CEP Microzona
    @GetMapping
    public ResponseEntity<List<FaixasCEPMicrozona>> findAll() {
        List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
        return ResponseEntity.ok().body(faixasCEPMicrozona);
    }    

    
    // Busca na Faixa de CEP da Microzona
    @GetMapping(path = "/{codigoMicrozona}/{codigoSequencial}")
    public ResponseEntity<FaixasCEPMicrozona> findById(@PathVariable Integer codigoMicrozona,
                                                       @PathVariable Integer codigoSequencial) {
    FaixasCEPMicrozonaPK faixasCEPMicrozonaPK = new FaixasCEPMicrozonaPK();
    faixasCEPMicrozonaPK.setCodigoMicrozona(codigoMicrozona);
    faixasCEPMicrozonaPK.setCodigoSequencial(codigoSequencial);
    
    FaixasCEPMicrozona faixasCEPMicrozonaBusca = faixasCEPMicrozonaService.findById(faixasCEPMicrozonaPK);
    
    return ResponseEntity.ok().body(faixasCEPMicrozonaBusca);
    }
     
    
    // Inclui Faixas de CEP da Microzona
    @PostMapping
    public ResponseEntity<Void> addFaixasCEPMicrozona(@RequestBody FaixasCEPMicrozona faixasCEPMicrozona) {
        FaixasCEPMicrozona faixasCEPMicrozonaNova = faixasCEPMicrozonaService.addFaixasCEPMicrozona(faixasCEPMicrozona);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{codigoMicrozona}")
                        .buildAndExpand(faixasCEPMicrozonaNova.getFaixasCEPMicrozonaPK().getCodigoMicrozona())
                        .toUri();
        return ResponseEntity.created(uri).build();
    }
        
    // Altera Filial
    @PutMapping(path = "/{codigoMicrozona}/{codigoSequencial}")
    public ResponseEntity<Void> updateFaixasCEPMicrozona(@PathVariable Integer codigoMicrozona,
                                                         @PathVariable Integer codigoSequencial, 
                                                         @RequestBody FaixasCEPMicrozona faixasCEPMicrozona) {
        FaixasCEPMicrozonaPK faixasCEPMicrozonaPK = new FaixasCEPMicrozonaPK();
        faixasCEPMicrozonaPK.setCodigoMicrozona(codigoMicrozona);
        faixasCEPMicrozonaPK.setCodigoSequencial(codigoSequencial);        
        
        faixasCEPMicrozonaService.updateFaixasCEPMicrozona(faixasCEPMicrozonaPK, faixasCEPMicrozona);
        
        return ResponseEntity.noContent().build();
        
    }
    
    
    // Exclusão da Faixa de CEP da Microzona
    @DeleteMapping(path = "/{codigoMicrozona}/{codigoSequencial}")
    public ResponseEntity<Void> deletaFaixasCEPMicrozona(@PathVariable Integer codigoMicrozona,
                                                         @PathVariable Integer codigoSequencial) {

        FaixasCEPMicrozonaPK faixasCEPMicrozonaPK = new FaixasCEPMicrozonaPK();
        faixasCEPMicrozonaPK.setCodigoMicrozona(codigoMicrozona);
        faixasCEPMicrozonaPK.setCodigoSequencial(codigoSequencial);
        
        faixasCEPMicrozonaService.deletaFaixasCEPMicrozona(faixasCEPMicrozonaPK);
        
        return ResponseEntity.noContent().build();
    }
    
    
//    //-----------------------------------------------------------------------------------------------------
//    // Exclui Filial e chama Lista de Filiais
//    // method Post (página)
//    @PostMapping(path = "/remover/{empresa}/{codigo}")
//    public ModelAndView deletaFilialWeb(@PathVariable Integer codigoEmpresa, 
//                                        @PathVariable Integer codigoFilial,
//                                        @RequestBody Filial filial) {
//        filialService.deletaFilial(codigoEmpresa, codigoFilial, filial);
//        
//        List<Filial> filiais = filialService.findAll();
//        
//        ModelAndView modelAndView = new ModelAndView("FilialListar");
//        modelAndView.addObject("filiais", filiais);
//        
//        return modelAndView;
//    }         
//    
//    // Altera Filial
//    // method Post (página)
//    @GetMapping(path = "/editar/{empresa}/{codigo}")
//    public ModelAndView editarFilialaWeb(@PathVariable Integer codigoEmpresa,
//                                         @PathVariable Integer codigoFilial,
//                                         @RequestBody Filial filial) {
//        ModelAndView modelAndView = new ModelAndView("FilialFormulario");
//        
//        Filial filialAltera = filialService.findById(codigoEmpresa, codigoFilial, filial);
//        
//        modelAndView.addObject("filial", filialAltera);
//        
//        return modelAndView;
//    }    
}


