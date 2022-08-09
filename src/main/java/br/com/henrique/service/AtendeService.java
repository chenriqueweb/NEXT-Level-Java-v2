package br.com.henrique.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.ViaCepClient;
import br.com.henrique.model.Atende;
import br.com.henrique.model.Cep;
import br.com.henrique.model.Estado;
import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.model.Microzona;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.repository.AtendeRepository;
import br.com.henrique.repository.FaixasCEPMicrozonaRepository;
import br.com.henrique.repository.FilialRepository;
import br.com.henrique.repository.RotaEntregaRepository;
import br.com.henrique.service.exception.ObjectNotFoundException;

@Service
public class AtendeService {

	@Autowired
	private AtendeRepository repositAtende;

	@Autowired
	private FaixasCEPMicrozonaService faixasCEPMicrozonaService;

	@Autowired
	private MicrozonaService microzonaService;

	@Autowired
	private FaixasCEPMicrozonaRepository faixasCEPMicrozonaRepository;

	@Autowired
	private RotaEntregaRepository repositRotaEntrega;

	@Autowired
	private FilialRepository repositFilial;

	@Autowired
    private EstadoService estadoService;

	// @Autowired
	// private FaixasCEPMicrozonaService faixasCEPMicrozonaService;

	// Busca CEP de Atendimento de Filial
//    public Atende findById(Integer cep) {
//        Atende atende = repositAtende.findById(cep).orElse(null);
//        if (atende == null) {
//            throw new ObjectNotFoundException("CEP nao encontrado próximo de uma filial !");
//        }        
//        return atende;
//    }

	// Busca CEP de Atendimento de Filial
	public Atende retornaCEP(Integer cepAtende) {
    	
    	Atende atende = new Atende();
    	
    	List<FaixasCEPMicrozona> faixasCEPMicrozona = faixasCEPMicrozonaService.findAll();
        //JSONObject objetoJson = new JSONObject();
    	
        // Procura por uma faixa de CEPs
        for (int x = 0; x < faixasCEPMicrozona.size(); x++ ) {
          if (cepAtende >= faixasCEPMicrozona.get(x).getCEPinicial() & 
              cepAtende <= faixasCEPMicrozona.get(x).getCEPfinal()) {    	
           
        	  Cep cep = ViaCepClient.findCep(cepAtende.toString());  // 14620000
           
        	  // Dados do CEP informado
        	  if (cep.getCep() != null) {
        		  atende.setCep(cep.getCep());
        		  atende.setLogradouro(cep.getLogradouro());
        		  atende.setComplemento(cep.getComplemento());
        		  atende.setLocalidade(cep.getLocalidade());  // Cidade - Municipi
        		  atende.setBairro(cep.getBairro());
        		  atende.setUf(cep.getUf());
        		  atende.setIbge(cep.getIbge());
        	  } else {
        		  throw new ObjectNotFoundException("Não foi encontrada uma Filial próxima ao CEP informado");
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
                
        	  atende.setCepRequisitado(cep.getCep());
        	  atende.setEmpresaAtende(rotaEntregaBusca.get().getCodigoEmpresa());
        	  atende.setFilialAtende(rotaEntregaBusca.get().getCodigoFilial());
        	  atende.setNomeFilial(filialBusca.get().getNome());
        	  atende.setCnpjFilial(filialBusca.get().getCnpj());
       
        	  atende.setMicrozona(microzona.getCodigo());
        	  atende.setUfRota(microzona.getEstadoRota().getSigla());
        	  atende.setCodigoRota(microzona.getCodigoRota());             
        	  atende.setCodigoMunicipio(microzona.getCodigoMunicipio().getCodigo_ID());

        	  // Informarções do Cliente
        	  atende.setMunicipio(cep.getLocalidade());
        	  atende.setEstado(cep.getUf());
        	  atende.setNomeEstado(estadoBusca.getNome());
          }
      }
      
      // Caso não encontre nenhum CEP
  	  if (atende.getCep() == null) {
		  throw new ObjectNotFoundException("Não foi encontrada uma Filial próxima ao CEP informado");
  	  }
        
      return atende;
    } 	
}