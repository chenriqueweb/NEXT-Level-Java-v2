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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.service.RotaEntregaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Rota_Entrega")
@ApiOperation(value = "CRUD - Rota de Entrega")
@RestController
@RequestMapping(path = "/rotaEntrega")
public class RotaEntregaController {
    
    @Autowired
    private RotaEntregaService rotaEntregaService;

    // Lista RotaEntrega
    @GetMapping
    @ApiOperation(value = "Lista todas as RotaEntregas")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de todas as RotaEntregas")
    })  
    public ResponseEntity<List<RotaEntrega>> findAll() {
        List<RotaEntrega> rotaEntregas = rotaEntregaService.findAll();
        return ResponseEntity.ok().body(rotaEntregas);
    }
    
    // Lista de Rotas de Entrega com paginação
    @GetMapping(path = "page")
    @ApiOperation(value = "Lista todas as RotaEntregas - paginação")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna uma lista de todas as RotaEntregas")
    })  
    public ResponseEntity<Page<RotaEntrega>> findAllPage(Pageable pageable) {
        return ResponseEntity.ok().body(rotaEntregaService.findAllPage(pageable));
    }       
    
    // Busca por RotaEntrega
    @GetMapping(path = "{siglaEstado}/{codigoRota}")
    @ApiOperation(value = "Busca por uma RotaEntrega")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna dados da RotaEntrega"),
    	    @ApiResponse(code = 404, message = "RotaEntrega não encontrada")    	    
    })  
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
    @ApiOperation(value = "Inclui uma RotaEntrega")
    @ApiResponses(value = {
    	    @ApiResponse(code = 201, message = "RotaEntrega criada com sucesso")
    }) 
    public ResponseEntity<Void> addRotaEntrega(@Valid @RequestBody RotaEntrega rotaEntrega) {
        RotaEntrega rotaEntregaNova = rotaEntregaService.addRotaEntrega(rotaEntrega);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{siglaEstado}/{codigoRota}")
                  .buildAndExpand(rotaEntregaNova.getRotaEntregaPK().getSiglaEstado(),
                                  rotaEntregaNova.getRotaEntregaPK().getCodigoRota())
                  .toUri();
        return ResponseEntity.created(uri).build();
    }
    
    // Altera RotaEntrega
    @PutMapping(path = "{siglaEstado}/{codigoRota}")
    @ApiOperation(value = "Altera os dados de uma RotaEntrega")
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "RotaEntrega alterada com sucesso"),
    	    @ApiResponse(code = 400, message = "Dados inválidos"),
    	    @ApiResponse(code = 404, message = "RotaEntrega não encontrada")    	    
    })  
    public ResponseEntity<Void> updateRotaEntrega(@Valid 
    		                                      @PathVariable String siglaEstado,
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
    @ApiOperation(value = "Exclui uma RotaEntrega")
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "RotaEntrega excluída"),
    	    @ApiResponse(code = 404, message = "RotaEntrega não encontrada"),
    	    @ApiResponse(code = 500, message = "Houve um erro e não foi possível excluir a RotaEntrega")
    })  
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