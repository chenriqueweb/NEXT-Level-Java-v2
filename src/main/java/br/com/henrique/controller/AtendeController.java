package br.com.henrique.controller;

import java.util.List;

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
import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.service.FaixasCEPMicrozonaService;

@RestController
@RequestMapping(path = "/atende")
public class AtendeController {
    
    @Autowired
    private FaixasCEPMicrozonaService faixasCEPMicrozonaService;
    
    @GetMapping(path = "{cepAtende}")    
    public ResponseEntity<JSONObject> cepAtende(@PathVariable Integer cepAtende) {
//      System.out.println(cepAtende);
        
        List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
        JSONObject objetoJson = new JSONObject();
        
        JSONArray arrayMicrozonaJson = new JSONArray();
        JSONArray arrayEnderecoJson = new JSONArray();

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
                
//                objetoJson.put(faixasCEPMicrozona.get(x).getClass().getdeclaretion, faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoMicrozona());

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
              // Dados do CEP da Microzona
              arrayMicrozonaJson.add("microzona: " + faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoMicrozona());
              arrayMicrozonaJson.add("sequencial: " + faixasCEPMicrozona.get(x).getFaixasCEPMicrozonaPK().getCodigoSequencial());
              arrayMicrozonaJson.add("cepInicial: " + faixasCEPMicrozona.get(x).getCEPinicial());
              arrayMicrozonaJson.add("cepFinal: " + faixasCEPMicrozona.get(x).getCEPfinal());                

              objetoJson.put("microzona", arrayMicrozonaJson);                
                

              // Dados do CEP informado
              if (cep.getCep() != null) {
                 arrayEnderecoJson.add("cep: " + cep.getCep());
                 arrayEnderecoJson.add("logradouro: " + cep.getLogradouro());
                 arrayEnderecoJson.add("complemento: " + cep.getComplemento());
                 arrayEnderecoJson.add("localidade: " + cep.getLocalidade());
                 arrayEnderecoJson.add("bairro: " + cep.getBairro());
                 arrayEnderecoJson.add("uf: " + cep.getUf());
                 arrayEnderecoJson.add("ibge: " + cep.getIbge());
                 
                 objetoJson.put("endereco", arrayEnderecoJson);
              }

            }
        }
        
        return ResponseEntity.ok().body(objetoJson);        
    }
    
}