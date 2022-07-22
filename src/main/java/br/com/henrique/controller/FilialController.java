package br.com.henrique.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.service.FilialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Filial")
@ApiOperation(value = "CRUD - Filial")
@RestController
@RequestMapping(path = "/filial")
public class FilialController {
    
    @Autowired
    private FilialService filialService;

    // Lista Filiais
    @GetMapping
    @ApiOperation(value = "Lista todas as Filiais")
    public ResponseEntity<List<Filial>> findAll() {
        List<Filial> filiais = filialService.findAll();
        return ResponseEntity.ok().body(filiais);
    }    
    
    // Lista de Filiais com paginação
    @GetMapping(path = "page")
    public ResponseEntity<Page<Filial>> findAllPage(Pageable pageable) {
        return ResponseEntity.ok().body(filialService.findAllPage(pageable));
    }     
    
    // Busca por Filial
    @ApiOperation(value = "Busca por uma Filial")
    @GetMapping(path = "/{codigoEmpresa}/{codigoFilial}")
    public ResponseEntity<Filial> findById(@PathVariable Integer codigoEmpresa, 
                                           @PathVariable Integer codigoFilial) {
        FilialPK filialPK = new FilialPK();
        filialPK.setCodigoEmpresa(codigoEmpresa);
        filialPK.setCodigoFilial(codigoFilial);
        
        Filial filialBusca = filialService.findById(filialPK);

        return ResponseEntity.ok().body(filialBusca);
    }
    
    // Inclui Filial
    @PostMapping
    @ApiOperation(value = "Inclui uma Filial")
    public ResponseEntity<Void> addFilial(@RequestBody Filial filial) {
        Filial filialNova = filialService.addFilial(filial);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigoEmpresa}")
                  .buildAndExpand(filialNova.getFilialPK().getCodigoEmpresa())
                  .toUri();
        return ResponseEntity.created(uri).build();
    }    
    
    // Altera Filial
    @PutMapping(path = "/{codigoEmpresa}/{codigoFilial}")
    @ApiOperation(value = "Altera os dados de uma Filial")
    public ResponseEntity<Void> updateFilial(@PathVariable Integer codigoEmpresa,
                                             @PathVariable Integer codigoFilial, 
                                             @RequestBody Filial filial) {
        
        FilialPK filialPK = new FilialPK();
        filialPK.setCodigoEmpresa(codigoEmpresa);
        filialPK.setCodigoFilial(codigoFilial);          
        
        filialService.updateFilial(filialPK, filial);
        
        return ResponseEntity.noContent().build();
    }
    
    // Exclusão Filial
    @DeleteMapping(path = "/{codigoEmpresa}/{codigoFilial}")
    @ApiOperation(value = "Exclui uma Filial")
    public ResponseEntity<Void> deletaFilial(@PathVariable Integer codigoEmpresa, 
                                             @PathVariable Integer codigoFilial) {
        FilialPK filialPK = new FilialPK();
        filialPK.setCodigoEmpresa(codigoEmpresa);
        filialPK.setCodigoFilial(codigoFilial);        
        
        filialService.deletaFilial(filialPK);
        
        return ResponseEntity.noContent().build();
    }
    
    //-----------------------------------------------------------------------------------------------------
    // Exclui Filial e chama Lista de Filiais
    // method Post (página)
    @PostMapping(path = "/remover/{codigoEmpresa}/{codigoFilial}")
    public ModelAndView deletaFilialWeb(@PathVariable Integer codigoEmpresa, 
                                        @PathVariable Integer codigoFilial) {
        
        FilialPK filialPK = new FilialPK();
        filialPK.setCodigoEmpresa(codigoEmpresa);
        filialPK.setCodigoFilial(codigoFilial);          
        
        filialService.deletaFilial(filialPK);
        
        List<Filial> filiais = filialService.findAll();
        
        ModelAndView modelAndView = new ModelAndView("FilialListar");
        modelAndView.addObject("filiais", filiais);
        
        return modelAndView;
    }         
    
    // Altera Filial
    // method Post (página)
    @GetMapping(path = "/editar/{codigoEmpresa}/{codigoFilial}")
    public ModelAndView editarFilialaWeb(@PathVariable Integer codigoEmpresa,
                                         @PathVariable Integer codigoFilial) {
        
        FilialPK filialPK = new FilialPK();
        filialPK.setCodigoEmpresa(codigoEmpresa);
        filialPK.setCodigoFilial(codigoFilial);              
        Filial filialAltera = filialService.findById(filialPK);
        
        ModelAndView modelAndView = new ModelAndView("FilialFormulario");
        modelAndView.addObject("filial", filialAltera);
        
        return modelAndView;
    }    
}


