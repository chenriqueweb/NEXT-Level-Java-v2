package br.com.henrique.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.FaixasCEPMicrozonaPK;
import br.com.henrique.service.FaixasCEPMicrozonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Faixa_CEP_Microzona")
@ApiOperation(value = "CRUD - Faixa de CEPs Microzona")
@RestController
@RequestMapping(path = "/faixasCEPMicrozona")
public class FaixasCEPMicrozonaController {
    
    @Autowired
    private FaixasCEPMicrozonaService faixasCEPMicrozonaService;

    // Lista Faixas de CEP Microzona
    @GetMapping
    @ApiOperation(value = "Lista todas as Faixas de CEP Microzona")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de Faixas de CEP Microzona")
    }) 
    public ResponseEntity<List<FaixasCEPMicrozona>> findAll() {
        List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
        return ResponseEntity.ok().body(faixasCEPMicrozona);
    }    

    // Lista de Faixas de CEP Microzona com paginação
    @GetMapping(path = "page")
    @ApiOperation(value = "Lista todas as Faixas de CEP Microzona - paginação")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de Faixas de CEP Microzona")
    }) 
    public ResponseEntity<Page<FaixasCEPMicrozona>> findAllPage(Pageable pageable) {
        return ResponseEntity.ok().body(faixasCEPMicrozonaService.findAllPage(pageable));
    } 
    
    // Busca na Faixa de CEP da Microzona
    @ApiOperation(value = "Busca por uma Faixa de CEP da Microzona")
    @GetMapping(path = "/{codigoMicrozona}/{codigoSequencial}")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna dados da Faixa de CEP da Microzona"),
    	    @ApiResponse(code = 404, message = "Faixa de CEP da Microzona não encontrada")    	    
    })  
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
    @ApiOperation(value = "Inclui uma Faixa de CEP da Microzona")
    @ApiResponses(value = {
    	    @ApiResponse(code = 201, message = "Faixa de CEP da Microzona criada com sucesso")
    }) 
    public ResponseEntity<Void> addFaixasCEPMicrozona(@Valid @RequestBody FaixasCEPMicrozona faixasCEPMicrozona) {
        FaixasCEPMicrozona faixasCEPMicrozonaNova = faixasCEPMicrozonaService.addFaixasCEPMicrozona(faixasCEPMicrozona);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{codigoMicrozona}")
                        .buildAndExpand(faixasCEPMicrozonaNova.getFaixasCEPMicrozonaPK().getCodigoMicrozona())
                        .toUri();
        return ResponseEntity.created(uri).build();
    }
        
    // Altera Faixa de CEP da Microzona
    @PutMapping(path = "/{codigoMicrozona}/{codigoSequencial}")
    @ApiOperation(value = "Altera os dados de uma Faixa de CEP da Microzona")
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Faixa de CEP da Microzona alterada com sucesso"),
    	    @ApiResponse(code = 400, message = "Dados inválidos"),
    	    @ApiResponse(code = 404, message = "Faixa de CEP da Microzona não encontrada")    	    
    })
    public ResponseEntity<Void> updateFaixasCEPMicrozona(@Valid 
    		                                             @PathVariable Integer codigoMicrozona,
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
    @ApiOperation(value = "Exclui uma Faixa de CEP da Microzona")
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Faixa de CEP da Microzona excluída"),
    	    @ApiResponse(code = 404, message = "Faixa de CEP da Microzona não encontrada"), 
    	    @ApiResponse(code = 500, message = "Houve um erro e não foi possível excluir a Faixa de CEP da Microzona")    	    
    })  
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


