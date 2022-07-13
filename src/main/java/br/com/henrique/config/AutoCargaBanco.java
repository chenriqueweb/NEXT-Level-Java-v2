package br.com.henrique.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.henrique.model.Empresa;
import br.com.henrique.model.Estado;
import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.FaixasCEPMicrozonaPK;
import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.model.Microzona;
import br.com.henrique.model.Municipio;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.model.RotaEntregaPK;
import br.com.henrique.service.EmpresaService;
import br.com.henrique.service.EstadoService;
import br.com.henrique.service.FaixasCEPMicrozonaService;
import br.com.henrique.service.FilialService;
import br.com.henrique.service.MicrozonaService;
import br.com.henrique.service.MunicipioService;
import br.com.henrique.service.RotaEntregaService;

@Configuration
public class AutoCargaBanco implements CommandLineRunner {
    
    @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private EstadoService estadoService;    
    
    @Autowired
    private MunicipioService municipioService;  
    
    @Autowired
    private FilialService filialService;       
    
    @Autowired
    private FaixasCEPMicrozonaService faixasCEPMicrozonaService;    
 
    @Autowired
    private MicrozonaService microzonaService;    
  
    
    @Autowired
    private RotaEntregaService rotaEntregaService;
    
    @Override
    public void run(String...args) throws Exception { 
        
        // Carga da Tabela: EMPRESA
        Empresa empresa1 = new Empresa(null, "teste-01", "111.456.789/0001-01", "2022-06-25");
        Empresa empresa2 = new Empresa(null, "teste-02", "222.456.789/0001-01", "2022-06-26");
        Empresa empresa3 = new Empresa(null, "teste-03", "333.456.789/0001-01", "2022-06-27");
        Empresa empresa4 = new Empresa(null, "teste-04", "333.456.789/0001-01", "2022-06-27");
       
        empresaService.addEmpresa(empresa1);
        empresaService.addEmpresa(empresa2);
        empresaService.addEmpresa(empresa3);
        empresaService.addEmpresa(empresa4);  

        
        
        // Carga da Tabela: ESTADO        
        Estado estadoRJ = new Estado("RJ", "Rio de Janeiro");
        Estado estadoSP = new Estado("SP","Sao Paulo");
        Estado estadoMG = new Estado("MG","Minas Gerais");
        Estado estadoES = new Estado("ES","Espirito Santo");

        estadoService.addEstado(estadoRJ);       
        estadoService.addEstado(estadoSP);
        estadoService.addEstado(estadoMG);       
        estadoService.addEstado(estadoES); 
        
        
        
        // Carga da Tabela: MUNICIPIO
        Municipio municipioRJ1 = new Municipio(null, "Barra do Pirai", estadoRJ);
        Municipio municipioRJ2 = new Municipio(null, "Volta Redonda", estadoRJ);
        Municipio municipioRJ3 = new Municipio(null, "Pirai", estadoRJ);        
        Municipio municipioSP1 = new Municipio(null, "São Caetano", estadoSP);
        Municipio municipioSP2 = new Municipio(null, "Jundiai", estadoSP);

        municipioService.addMunicipio(municipioRJ1);
        municipioService.addMunicipio(municipioRJ2);
        municipioService.addMunicipio(municipioRJ3);        
        municipioService.addMunicipio(municipioSP1);
        municipioService.addMunicipio(municipioSP2);     
        
        
        
        // Carga da Tabela: FILIAL
        FilialPK filialPK = new FilialPK();
        filialPK.setCodigoEmpresa(1);
        filialPK.setCodigoFilial(1);
        Filial filial1 = new Filial(filialPK, "Filial-01", "123.456.0001/01", 1);
        filialService.addFilial(filial1);

        filialPK.setCodigoEmpresa(1);
        filialPK.setCodigoFilial(2);
        Filial filial2 = new Filial(filialPK, "Filial-02", "222.456.0001/01", 2);
        filialService.addFilial(filial2);
        
        
        
        // Carga da Tabela: MICROZONA
        Microzona microzona1 = new Microzona(null, "Microzona-01", "A", "N", "S", "N", "S", "N", "S", "N", estadoRJ, municipioRJ1, 1);
        Microzona microzona2 = new Microzona(null, "Microzona-02", "A", "N", "S", "N", "S", "N", "S", "N", estadoRJ, municipioRJ2, 1);
        Microzona microzona3 = new Microzona(null, "Microzona-03", "A", "N", "S", "N", "S", "N", "S", "N", estadoRJ, municipioRJ3, 1);
        Microzona microzona4 = new Microzona(null, "Microzona-04", "A", "N", "S", "N", "S", "N", "S", "N", estadoSP, municipioSP1, 1);
        Microzona microzona5 = new Microzona(null, "Microzona-05", "A", "N", "S", "N", "S", "N", "S", "N", estadoSP, municipioSP2, 1);
        
        microzonaService.addMicrozona(microzona1);
        microzonaService.addMicrozona(microzona2);        
        microzonaService.addMicrozona(microzona3);
        microzonaService.addMicrozona(microzona4);
        microzonaService.addMicrozona(microzona5);        
        
        
        
        // Carga da Tabela: ROTA DE ENTREGA
        RotaEntregaPK rotaEntregaPK = new RotaEntregaPK();

        rotaEntregaPK.setSiglaEstado("RJ");        

        rotaEntregaPK.setCodigoRota(1);
        RotaEntrega rotaEntrega1 = new RotaEntrega(rotaEntregaPK, "Rota-01", "A", 1, 1, 10);
        rotaEntregaService.addRotaEntrega(rotaEntrega1);

        rotaEntregaPK.setCodigoRota(2);
        RotaEntrega rotaEntrega2 = new RotaEntrega(rotaEntregaPK, "Rota-02", "A", 1, 1, 2);
        rotaEntregaService.addRotaEntrega(rotaEntrega2);

        rotaEntregaPK.setCodigoRota(3);
        RotaEntrega rotaEntrega3 = new RotaEntrega(rotaEntregaPK, "Rota-03", "I", 1, 1, 5);
        rotaEntregaService.addRotaEntrega(rotaEntrega3);
        
        rotaEntregaPK.setSiglaEstado("SP");        

        rotaEntregaPK.setCodigoRota(4);
        RotaEntrega rotaEntrega4 = new RotaEntrega(rotaEntregaPK, "Rota-04", "A", 1, 1, 20);
        rotaEntregaService.addRotaEntrega(rotaEntrega4);

        rotaEntregaPK.setCodigoRota(5);
        RotaEntrega rotaEntrega5 = new RotaEntrega(rotaEntregaPK, "Rota-05", "I", 1, 1, 3);        
        rotaEntregaService.addRotaEntrega(rotaEntrega5);        

        
        
        // Carga da Tabela: FAIXAS DE MICROZONA
        FaixasCEPMicrozonaPK faixasCEPMicrozonaPK = new FaixasCEPMicrozonaPK();
        
        faixasCEPMicrozonaPK.setCodigoMicrozona(1);
        faixasCEPMicrozonaPK.setCodigoSequencial(1);
        FaixasCEPMicrozona faixasCEPMicrozona1 = new FaixasCEPMicrozona(faixasCEPMicrozonaPK, 1000, 2000);
        faixasCEPMicrozonaService.addFaixasCEPMicrozona(faixasCEPMicrozona1);
        
        faixasCEPMicrozonaPK.setCodigoMicrozona(1);
        faixasCEPMicrozonaPK.setCodigoSequencial(2);
        FaixasCEPMicrozona faixasCEPMicrozona2 = new FaixasCEPMicrozona(faixasCEPMicrozonaPK, 2001, 3000);
        faixasCEPMicrozonaService.addFaixasCEPMicrozona(faixasCEPMicrozona2);
        
        faixasCEPMicrozonaPK.setCodigoMicrozona(1);
        faixasCEPMicrozonaPK.setCodigoSequencial(3);
        FaixasCEPMicrozona faixasCEPMicrozona3 = new FaixasCEPMicrozona(faixasCEPMicrozonaPK, 3001, 4000);
        faixasCEPMicrozonaService.addFaixasCEPMicrozona(faixasCEPMicrozona3);
        
        faixasCEPMicrozonaPK.setCodigoMicrozona(2);
        faixasCEPMicrozonaPK.setCodigoSequencial(1);
        FaixasCEPMicrozona faixasCEPMicrozona4 = new FaixasCEPMicrozona(faixasCEPMicrozonaPK, 4001, 5000);
        faixasCEPMicrozonaService.addFaixasCEPMicrozona(faixasCEPMicrozona4);
        
        faixasCEPMicrozonaPK.setCodigoMicrozona(3);
        faixasCEPMicrozonaPK.setCodigoSequencial(1);
        FaixasCEPMicrozona faixasCEPMicrozona5 = new FaixasCEPMicrozona(faixasCEPMicrozonaPK, 5001, 6000);
        faixasCEPMicrozonaService.addFaixasCEPMicrozona(faixasCEPMicrozona5);
        
//      @GetMapping("/empresa")
//      public List<Empresa> listar() {
//          var empresa1 = new Empresa();
//          empresa1.setCodigo(1L);
//          empresa1.setRazaoSocial("Empresa 1");
//          empresa1.setRaizCNPJ("111.456.0001/01");
//          empresa1.setDataAbertura("2002-06-27");
//          
//          var empresa2 = new Empresa();
//          empresa1.setCodigo(2L);
//          empresa1.setRazaoSocial("Empresa 2");
//          empresa1.setRaizCNPJ("222.456.0001/01");
//          empresa1.setDataAbertura("2002-06-24");
//          
//          return Arrays.asList(empresa1, empresa2);
//      }
        
        
    }
    
}
