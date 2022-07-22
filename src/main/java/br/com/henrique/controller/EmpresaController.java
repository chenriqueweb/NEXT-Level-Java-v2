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

import br.com.henrique.model.Empresa;
import br.com.henrique.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Empresa")
@RestController
@RequestMapping(path = "/empresa")
public class EmpresaController {
    
    @Autowired
    private EmpresaService empresaService;

    // Lista Empresa
    @GetMapping
    @ApiOperation(value = "Lista todas as Empresas")
    public ResponseEntity<List<Empresa>> findAll() {
        List<Empresa> empresas = empresaService.findAll();
        return ResponseEntity.ok().body(empresas);
    }
    
    // Lista de Empresas com paginação
    @GetMapping(path = "page")
    public ResponseEntity<Page<Empresa>> findAllPage(Pageable pageable) {
        return ResponseEntity.ok().body(empresaService.findAllPage(pageable));
    }    
    
    // Busca por Empresa
    @GetMapping(path = "{codigo}")
    @ApiOperation(value = "Busca por uma Empresa")
    public ResponseEntity<Empresa> findById(@PathVariable Integer codigo) {
        Empresa empresa = empresaService.findById(codigo);
        return ResponseEntity.ok().body(empresa);
    }
    
    // Inclui Empresa
    @PostMapping
    @ApiOperation(value = "Inclui uma Empresa")
    public ResponseEntity<Void> addEmpresa(@RequestBody Empresa empresa) {
        Empresa empresaNova = empresaService.addEmpresa(empresa);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(empresaNova.getCodigo()).toUri();
        return ResponseEntity.created(uri).build();
    }    
    
    // Altera Empresa
    @PutMapping(path = "{codigo}")
    @ApiOperation(value = "Altera os dados de uma Empresa")
    public ResponseEntity<Void> updateEmpresa(@PathVariable Integer codigo, @RequestBody Empresa empresa) {
        empresaService.updateEmpresa(codigo, empresa);
        return ResponseEntity.noContent().build();
    }
    
    // Exclusão Empresa
    @DeleteMapping(path = "{codigo}")
    @ApiOperation(value = "Exclui uma Empresa")
    public ResponseEntity<Void> deletaEmpresa(@PathVariable Integer codigo) {
        empresaService.deletaEmpresa(codigo);
        return ResponseEntity.noContent().build();
    }
    
    //-----------------------------------------------------------------------------------------------------
    // Altera empresa
    // method Post (página)
    @GetMapping(path = "/editar/{codigo}")
    public ModelAndView editarEmpresaWeb(@PathVariable Integer codigo) {
        ModelAndView modelAndView = new ModelAndView("EmpresaFormulario");
        
        Empresa empresa = empresaService.findById(codigo);
        
        modelAndView.addObject("empresa", empresa);
        
        return modelAndView;
    }

    // Exclui empresa e chama Lista de Empresas
    // method Post (página)
    @PostMapping(path = "/remover/{codigo}")
    public ModelAndView deletaEmpresaWeb(@PathVariable Integer codigo) {
        empresaService.deletaEmpresa(codigo);
        
        List<Empresa> empresas = empresaService.findAll();
        
        ModelAndView modelAndView = new ModelAndView("EmpresaListar");
        modelAndView.addObject("empresas", empresas);
        
        return modelAndView;
    }         
    
}