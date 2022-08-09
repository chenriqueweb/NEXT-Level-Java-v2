package br.com.henrique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.henrique.model.Atende;
import br.com.henrique.service.AtendeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Busca_CEP")
@ApiOperation(value = "CRUD - Atende")
@RestController
@RequestMapping(path = "/atende")
public class AtendeController {
    
    @Autowired
    private AtendeService atendeService;

    
    // Busca pelo CEP próximo da Filial
    @GetMapping(path = "{cepAtende}")
    @ApiOperation(value = "Consulta de Filiais por CEP")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna a Filial por CEP"),
    	    @ApiResponse(code = 400, message = "CEP inválido"),
    	    @ApiResponse(code = 404, message = "Não foi encontrada uma Filial próxima ao CEP informado"),
    	    @ApiResponse(code = 500, message = "Houve um erro e não foi possível encontrar uma Filial próxima ao CEP informado")    	    
    })  
    public ResponseEntity<Atende> cepAtende(@PathVariable Integer cepAtende) {
    	
        Atende atende = atendeService.retornaCEP(cepAtende);
        
        return ResponseEntity.ok().body(atende);
    }    
}
