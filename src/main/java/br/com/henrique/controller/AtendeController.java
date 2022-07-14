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
//      System.out.println(cepAtende);
        
        List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
        JSONObject objetoJson = new JSONObject();
        
        JSONArray arrayMicrozonaJson = new JSONArray();
        JSONArray arrayEnderecoJson  = new JSONArray();
        
        // Procura por uma faixa de CEPs
        for (int x = 0; x < faixasCEPMicrozona.size(); x++ ) {
          if (cepAtende >= faixasCEPMicrozona.get(x).getCEPinicial() & 
              cepAtende <= faixasCEPMicrozona.get(x).getCEPfinal()) {
//                System.out.println("--------------------------------------------------------------------------------------------");
//                System.out.println("== Faixa:");
//                System.out.println("Microzona: " + faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoMicrozona());
//                System.out.println("Sequencial: " + faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoSequencial());
//                System.out.println("CEPInicial:" + faixasCEPMicrozona.get(x).getCEPinicial());
//                System.out.println("CEPFinal:" + faixasCEPMicrozona.get(x).getCEPfinal());
                
                Cep cep = ViaCepClient.findCep("07807220");  // 14620000
              
//              System.out.println(cep.getCep());
//              System.out.println(cep.getBairro());
//              System.out.println(cep.getComplemento());
//              System.out.println(cep.getDdd());
//              System.out.println(cep.getGia());
//              System.out.println(cep.getIbge());
//              System.out.println(cep.getLocalidade());
//              System.out.println(cep.getLogradouro());
//              System.out.println(cep.getSiafi());
//              System.out.println(cep.getUf());
//
//              // Dados do CEP da Microzona
//              arrayMicrozonaJson.add("microzona: " + faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoMicrozona());
//              arrayMicrozonaJson.add("sequencial: " + faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoSequencial());
//              arrayMicrozonaJson.add("cepInicial: " + faixasCEPMicrozona.get(x).getCEPinicial());
//              arrayMicrozonaJson.add("cepFinal: " + faixasCEPMicrozona.get(x).getCEPfinal());                
//
//              objetoJson.put("microzona", arrayMicrozonaJson);       
                
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
              arrayMicrozonaJson.add("empresaAtende: " + rotaEntregaBusca.get().getCodigoEmpresa());
              arrayMicrozonaJson.add("filialAtende: " + rotaEntregaBusca.get().getCodigoFilial());
              arrayMicrozonaJson.add("nomeFilial: " + filialBusca.get().getNome());
              arrayMicrozonaJson.add("cnpjFilial: " + filialBusca.get().getCnpj());
              arrayMicrozonaJson.add("microzona: " + faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoMicrozona());
              arrayMicrozonaJson.add("ufRota: " + microzona.getEstadoRota().getSigla());
              arrayMicrozonaJson.add("codigoRota: " + microzona.getCodigoRota());             
              
//              arrayMicrozonaJson.add("codigoMunicipio: " + microzona.getCodigoMunicipio().getCodigo_ID());          
//              arrayMicrozonaJson.add("municipio: " + microzona.getCodigoMunicipio().getNome());
//              arrayMicrozonaJson.add("estado: " + microzona.getCodigoMunicipio().getEstado().getSigla());
//              arrayMicrozonaJson.add("nomeEstado: " + microzona.getCodigoMunicipio().getEstado().getNome());
              
              arrayMicrozonaJson.add("codigoMunicipioIBGE: " + cep.getIbge());
              arrayMicrozonaJson.add("municipio: " + cep.getLocalidade());
              arrayMicrozonaJson.add("estado: " + cep.getUf());
              arrayMicrozonaJson.add("nomeEstado: " + estadoBusca.getNome());

              objetoJson.put("response", arrayMicrozonaJson);
            }
        }
        
        return ResponseEntity.ok().body(objetoJson);        
    }
    
}