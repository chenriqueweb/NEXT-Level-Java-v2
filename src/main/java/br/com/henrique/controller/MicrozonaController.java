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

import br.com.henrique.model.Microzona;
import br.com.henrique.service.MicrozonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Microzona")
@ApiOperation(value = "CRUD - Microzona")
@RestController
@RequestMapping(path = "/microzona")
public class MicrozonaController {

    @Autowired
    private MicrozonaService microzonaService;

    // Lista Microzona
    @GetMapping
    @ApiOperation(value = "Lista todas as Microzonas")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de todas as Microzonas")
    })    
    public ResponseEntity<List<Microzona>> findAll() {
        List<Microzona> microzonas = microzonaService.findAll();
        return ResponseEntity.ok().body(microzonas);
    }
    
    // Lista de Microzonas com paginação
    @GetMapping(path = "page")
    @ApiOperation(value = "Lista todas as Microzonas - paginação")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de todas as Microzonas")
    })    
    public ResponseEntity<Page<Microzona>> findAllPage(Pageable pageable) {
        return ResponseEntity.ok().body(microzonaService.findAllPage(pageable));
    }       
    
    // Busca por Microzona
    @GetMapping(path = "{codigo}")
    @ApiOperation(value = "Busca por uma Microzona")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna dados da Microzona"),
    	    @ApiResponse(code = 404, message = "Microzona não encontrada")    	    
    })  
    public ResponseEntity<Microzona> findById(@PathVariable Integer codigo) {
        Microzona microzona = microzonaService.findById(codigo);
        return ResponseEntity.ok().body(microzona);
    }    
    
    // Inclui Micrzona
    @PostMapping
    @ApiOperation(value = "Inclui uma Micrzona")
    @ApiResponses(value = {
    	    @ApiResponse(code = 201, message = "Micrzona criada com sucesso")
    }) 
    public ResponseEntity<Void> addMicrozona(@Valid @RequestBody Microzona microzona) {
        Microzona microzonaNova = microzonaService.addMicrozona(microzona);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(microzonaNova.getCodigo()).toUri();
        return ResponseEntity.created(uri).build();
    }        

    // Altera Microzona
    @PutMapping(path = "{codigo}")
    @ApiOperation(value = "Altera os dados de uma Microzona")
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Microzona alterada com sucesso"),
    	    @ApiResponse(code = 400, message = "Dados inválidos"),
    	    @ApiResponse(code = 404, message = "Microzona não encontrada")    	    
    }) 
    public ResponseEntity<Void> updateMicrozona(@Valid 
    		                                    @PathVariable Integer codigo, 
                                                @RequestBody Microzona microzona) {
        microzonaService.updateMicrozona(codigo, microzona);
        return ResponseEntity.noContent().build();
    }    
        
    // Exclusão Microzona
    @DeleteMapping(path = "{codigo}")
    @ApiOperation(value = "Exclui uma Microzona")
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Microzona excluída"),
    	    @ApiResponse(code = 404, message = "Microzona não encontrada"), 
    	    @ApiResponse(code = 500, message = "Houve um erro e não foi possível excluir a Microzona")    	    
    })
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
