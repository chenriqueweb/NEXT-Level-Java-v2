package br.com.henrique.controller;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.henrique.ViaCepClient;
import br.com.henrique.model.Cep;
import br.com.henrique.model.Estado;
import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.model.Microzona;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.repository.FilialRepository;
import br.com.henrique.repository.RotaEntregaRepository;
import br.com.henrique.service.EstadoService;
import br.com.henrique.service.FaixasCEPMicrozonaService;
import br.com.henrique.service.MicrozonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Busca_CEP")
@ApiOperation(value = "Consulta de Filiais por CEP")
@RestController
@RequestMapping(path = "/atende")
public class AtendeController {
    
    @Autowired
    private FaixasCEPMicrozonaService faixasCEPMicrozonaService;
    
    @Autowired
    private MicrozonaService microzonaService;
    
    @Autowired
    private RotaEntregaRepository repositRotaEntrega;    
    
    @Autowired
    private FilialRepository repositFilial;    
    
    @Autowired
    private EstadoService estadoService;    

    @SuppressWarnings("unchecked")
    @GetMapping(path = "{cepAtende}")    
    public ResponseEntity<JSONObject> cepAtende(@PathVariable Integer cepAtende) {
        
        List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
        JSONObject objetoJson = new JSONObject();
        
        JSONArray arrayMicrozonaJson = new JSONArray();
        JSONArray arrayEnderecoJson  = new JSONArray();
        
        // Procura por uma faixa de CEPs
        for (int x = 0; x < faixasCEPMicrozona.size(); x++ ) {
          if (cepAtende >= faixasCEPMicrozona.get(x).getCEPinicial() & 
              cepAtende <= faixasCEPMicrozona.get(x).getCEPfinal()) {
                Cep cep = ViaCepClient.findCep(cepAtende.toString());  // 14620000
              
//              System.out.println(cep.getDdd());
//              System.out.println(cep.getGia());
//              System.out.println(cep.getSiafi());
//
              // Dados do CEP informado
              if (cep.getCep() != null) {
                 arrayEnderecoJson.add("cep: " + cep.getCep());
                 arrayEnderecoJson.add("logradouro: " + cep.getLogradouro());
                 arrayEnderecoJson.add("complemento: " + cep.getComplemento());
                 arrayEnderecoJson.add("localidade: " + cep.getLocalidade());  // Cidade - Municipio
                 arrayEnderecoJson.add("bairro: " + cep.getBairro());
                 arrayEnderecoJson.add("uf: " + cep.getUf());
                 arrayEnderecoJson.add("ibge: " + cep.getIbge());
                 
                 objetoJson.put("endereco", arrayEnderecoJson);
              }
              
              // Busca de Informações da Microzona
              Microzona microzona = microzonaService.findById(faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoMicrozona());
              
              // Busca por informações da Rota de Entrega
              RotaEntregaPK rotaEntregaPK = new RotaEntregaPK();
              rotaEntregaPK.setSiglaEstado(microzona.getEstadoRota().getSigla());
              rotaEntregaPK.setCodigoRota(microzona.getCodigoRota());
              
              Optional<RotaEntrega> rotaEntregaBusca = repositRotaEntrega.findById(rotaEntregaPK);
              
              // Busca por informações da Filial
              FilialPK filialPK = new FilialPK();
              filialPK.setCodigoEmpresa(rotaEntregaBusca.get().getCodigoEmpresa());
              filialPK.setCodigoFilial(rotaEntregaBusca.get().getCodigoFilial());
              
              Optional<Filial> filialBusca = repositFilial.findById(filialPK);        
              
              // Bsuca por informações do Estado
              Estado estadoBusca = estadoService.findById(cep.getUf());
              
              // Resultado da Busca por CEP x Rota de Entrega e Filial
              arrayMicrozonaJson.add("cepRequisitado: " + cepAtende);  // ok
              arrayMicrozonaJson.add("empresaAtende: " + rotaEntregaBusca.get().getCodigoEmpresa());  // Verificar na tabela DB2.ZEN (cd_empgcb_atd)
              arrayMicrozonaJson.add("filialAtende: " + rotaEntregaBusca.get().getCodigoFilial());
              arrayMicrozonaJson.add("nomeFilial: " + filialBusca.get().getNome());
              arrayMicrozonaJson.add("cnpjFilial: " + filialBusca.get().getCnpj());
              
              arrayMicrozonaJson.add("microzona: " + microzona.getCodigo());
              arrayMicrozonaJson.add("ufRota: " + microzona.getEstadoRota().getSigla());
              arrayMicrozonaJson.add("codigoRota: " + microzona.getCodigoRota());             
              arrayMicrozonaJson.add("codigoMunicipio: " + microzona.getCodigoMunicipio().getCodigo_ID());

              // Informarções do Cliente
              arrayMicrozonaJson.add("municipio: " + cep.getLocalidade());
              arrayMicrozonaJson.add("estado: " + cep.getUf());
              arrayMicrozonaJson.add("nomeEstado: " + estadoBusca.getNome());

              objetoJson.put("response", arrayMicrozonaJson);
            }
        }
        
        return ResponseEntity.ok().body(objetoJson);        
    }
    
    
    @GetMapping(path = "/filial")
    public ModelAndView filialAtendeWeb() {
        ModelAndView modelAndView = new ModelAndView("FilialAtende");
        
        return modelAndView;
    }
    
    @GetMapping(path = "/filial/{cep}")
    public ModelAndView filialAtendeBuscaWeb(@PathVariable Integer cep) throws ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView("FilialAtendeBusca");
        
        
//        Class filialAtendeBusca = Class.forName((this.cepAtende(cep).getBody()).toString());
//        modelAndView.addObject("filialAtendeBusca", filialAtendeBusca);
        
//        Object objectFilialAtendeBusca = this.cepAtende(cep).getBody();
//        modelAndView.addObject("filialAtendeBusca", objectFilialAtendeBusca);  // this.cepAtende(cep).getBody());  // this.cepAtende(cep));
        
        return modelAndView;
    }    
    
}