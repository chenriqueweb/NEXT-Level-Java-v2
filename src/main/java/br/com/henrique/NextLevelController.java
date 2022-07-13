package br.com.henrique;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.henrique.model.Empresa;
import br.com.henrique.model.Estado;
import br.com.henrique.model.FaixasCEPMicrozona;
import br.com.henrique.model.Filial;
import br.com.henrique.model.FilialPK;
import br.com.henrique.model.Microzona;
import br.com.henrique.model.Municipio;
import br.com.henrique.model.RotaEntrega;
import br.com.henrique.service.EmpresaService;
import br.com.henrique.service.EstadoService;
import br.com.henrique.service.FaixasCEPMicrozonaService;
import br.com.henrique.service.FilialService;
import br.com.henrique.service.MicrozonaService;
import br.com.henrique.service.MunicipioService;
import br.com.henrique.service.RotaEntregaService;

@Controller
public class NextLevelController {
    
        @Autowired
        private FilialService filialService;
        
        @Autowired
        private EmpresaService empresaService;
        
        @Autowired
        private EstadoService estadoService;
        
        @Autowired
        private MunicipioService municipioService;
        
        @Autowired
        private RotaEntregaService rotaEntregaService;     
        
        @Autowired
        private MicrozonaService microzonaService;

        @Autowired
        private FaixasCEPMicrozonaService faixasCEPMicrozonaService;

        
        // ### Página Principal
        @GetMapping("/")
        public String index() {
            return "index";
        }
        
        //--------------------------------------------------------------------------------------
        // ### Empresa
        @GetMapping("/empresaListar")
        public ModelAndView findAllEmpresa() {
            List<Empresa> empresas = empresaService.findAll();
            
            ModelAndView modelAndView = new ModelAndView("EmpresaListar");
            modelAndView.addObject("empresas", empresas);
            
            return modelAndView;
        }
        
        @GetMapping("/empresa/novo")
        public ModelAndView empresaNovo() {            
            ModelAndView modelAndView = new ModelAndView("EmpresaFormulario");
            modelAndView.addObject("empresa", new Empresa());
            
            return modelAndView;
        }        
        
        @PostMapping("/empresa/form")
        public String insereEmpresa(Empresa empresa) {
            empresaService.addEmpresa(empresa);
            
            return "redirect:/empresaListar";
        }

        // Atualiza dados da Empresa     
        // method Post (página)
        @PostMapping("/empresa/salvar/{codigo}")
        public String atualizaEmpresaWeb(Empresa empresa) {
            Empresa empresaAntes = empresaService.findById(empresa.getCodigo());
            
            empresaService.deletaEmpresa(empresaAntes.getCodigo());
            empresaService.addEmpresa(empresa);

            return "redirect:/empresaListar";        
        }        
        
        
        
        //--------------------------------------------------------------------------------------
        // ### Estado
        @GetMapping("/estadoListar")
        public ModelAndView findAllEstado() {
            List<Estado> estados = estadoService.findAll();
            
            ModelAndView modelAndView = new ModelAndView("EstadoListar");
            modelAndView.addObject("estados", estados);
            
            return modelAndView;
        }
        
        @GetMapping("/estado/novo")
        public ModelAndView estadoNovo() {            
            ModelAndView modelAndView = new ModelAndView("EstadoFormulario");
            modelAndView.addObject("estado", new Estado());
            
            return modelAndView;
        }
        
        @PostMapping("/estado/form")
        public String insreEstado(Estado estado) {
            estadoService.addEstado(estado);
            
            return "redirect:/estadoListar";
        }  
        
        // Atualiza dados da Estado     
        // method Post (página)
        @PostMapping("/estado/salvar/{sigla}")
        public String atualizaEstadoWeb(Estado estado) {
            Estado estadoAntes = estadoService.findById(estado.getSigla());
            
            estadoService.updateEstado(estadoAntes.getSigla(), estado);

            return "redirect:/estadoListar";        
        }            

        
        //--------------------------------------------------------------------------------------
        // ### Municipio
        @GetMapping("/municipioListar")
        public ModelAndView findAllMunicipio() {
            List<Municipio> municipios = municipioService.findAll();
            
            ModelAndView modelAndView = new ModelAndView("MunicipioListar");
            modelAndView.addObject("municipios", municipios);
            
            return modelAndView;
        }
        
        @GetMapping("/municipio/novo")
        public ModelAndView municipioNovo() {            
            ModelAndView modelAndView = new ModelAndView("MunicipioFormulario");
            modelAndView.addObject("municipio", new Municipio());
            
            return modelAndView;
        }    
        
        @PostMapping("/municipio/form")
        public String insreMunicipio1(Municipio municipio) {
            municipioService.addMunicipio(municipio);
            
            return "redirect:/municipioListar";
        }          
        
        // Atualiza dados do Municipio     
        // method Post (página)
        @PostMapping("/municipio/salvar/{codigo}")
        public String atualizaMunicipioWeb(Municipio municipio) {
            Municipio municipioAntes = municipioService.findById(municipio.getCodigo_ID());
            
            municipioService.deletaMunicipio(municipioAntes.getCodigo_ID());
            municipioService.addMunicipio(municipio);
            
//            municipioService.updateMunicipio(municipioAntes.getCodigo_ID(), municipio);

            return "redirect:/municipioListar";        
        } 
        
        
        //--------------------------------------------------------------------------------------
        // #### Filial 
        @GetMapping("/filialListar")
        public ModelAndView findAllFilial() {
            List<Filial> filiais = filialService.findAll();
            
            ModelAndView modelAndView = new ModelAndView("FilialListar");
            modelAndView.addObject("filiais", filiais);
            
            return modelAndView;
        }        
        
        @GetMapping("/filial/novo")
        public ModelAndView filialNovo() {            
            ModelAndView modelAndView = new ModelAndView("FilialFormulario");
            modelAndView.addObject("filial", new Filial());
            
            return modelAndView;
        }        
       
        @PostMapping("/filial/form")
        public String insereFilial(Filial filial) {
            filialService.addFilial(filial);
            
            return "redirect:/filialListar";
        }       
        
                
        // Atualiza dados da Filial     
        // method Post (página)
        @PostMapping("/filial/salvar/{codigoEmpresa}/{codigoFilial}")
        public String atualizaFilialWeb(@PathVariable Integer codigoEmpresa,
                                        @PathVariable Integer codigoFilial,
                                        Filial filial) {

            FilialPK filialPK = new FilialPK();
            filialPK.setCodigoEmpresa(codigoEmpresa);
            filialPK.setCodigoFilial(codigoFilial);              
            Filial filialAntes = filialService.findById(filialPK);            
            
            filialService.deletaFilial(filialPK);
            filialService.addFilial(filial);
            
//            filialService.updateFilial(filialAntes.getFilialPK(), filial);

            return "redirect:/filialListar";        
        } 
        
        
        //--------------------------------------------------------------------------------------                
        // ### Rota de Entrega
        @GetMapping("/rotaEntregaListar")
        public ModelAndView findAllRotaEntrega() {
            List<RotaEntrega> rotasEntregas = rotaEntregaService.findAll();
            
            ModelAndView modelAndView = new ModelAndView("RotaEntregaListar");
            modelAndView.addObject("rotasEntregas", rotasEntregas);
            
            return modelAndView;
        }
        
        @GetMapping("/rotaEntrega/novo")
        public ModelAndView rotaEntregaNovo() {            
            ModelAndView modelAndView = new ModelAndView("RotaEntregaFormulario");
            modelAndView.addObject("rotaEntrega", new RotaEntrega());
            
            return modelAndView;
        }            
        
        @PostMapping("/rotaEntrega/form")
        public String insereRotaEntrega(RotaEntrega rotaEntrega) {
            rotaEntregaService.addRotaEntrega(rotaEntrega);
            
            return "redirect:/rotaEntregaListar";
        }              
        
        // Atualiza dados da Rota de Entrega
        // method Post (página)
        @PostMapping("/rotaEntrega/salvar/{siglaEstado}/{codigo}")
        public String atualizaRotaEntregaoWeb(RotaEntrega rotaEntrega) {
            RotaEntrega rotaEntregaAntes = rotaEntregaService.findById(rotaEntrega.getRotaEntregaPK());
            
            rotaEntregaService.deletaRotaEntrega(rotaEntregaAntes.getRotaEntregaPK());
            rotaEntregaService.addRotaEntrega(rotaEntrega);

            return "redirect:/rotaEntregaListar";        
        }             
        

        //--------------------------------------------------------------------------------------            
        // ### Microzona
        @GetMapping("/microzonaListar")
        public ModelAndView findAllMicrozona() {
            List<Microzona> microzonas = microzonaService.findAll();
            
            ModelAndView modelAndView = new ModelAndView("MicrozonaListar");
            modelAndView.addObject("microzonas", microzonas);
            
            return modelAndView;
        }

        @GetMapping("/microzona/novo")
        public ModelAndView microzonaNovo() {            
            ModelAndView modelAndView = new ModelAndView("MicrozonaFormulario");
            modelAndView.addObject("microzona", new Microzona());
            
            return modelAndView;
        }            
        
        @PostMapping("/microzona/form")
        public String insereEmpresa(Microzona microzona) {
            microzonaService.addMicrozona(microzona);
            
            return "redirect:/microzonaListar";
        }        
        
        // Atualiza dados da Microzona     
        // method Post (página)
        @PostMapping("/microzona/salvar/{codigo}")
        public String atualizaMicrozonaWeb(Microzona microzona) {
            Microzona microzonaAntes = microzonaService.findById(microzona.getCodigo());
            
            microzonaService.deletaMicrozona(microzonaAntes.getCodigo());
            microzonaService.addMicrozona(microzona);

            return "redirect:/microzonaListar";        
        }             
        
        //--------------------------------------------------------------------------------------        
        // ### Faixa de CEPs Microzona
        @GetMapping("/faixasCEPMicrozonaListar")
        public ModelAndView findAllFaixaCEPMicrozona() {
            List<FaixasCEPMicrozona> faixasCEPMicrozonas = faixasCEPMicrozonaService.findAll();
            
            ModelAndView modelAndView = new ModelAndView("FaixasCEPMicrozonaListar");
            modelAndView.addObject("faixasCEPMicrozonas", faixasCEPMicrozonas);
            
            return modelAndView;
        }
        
        @GetMapping("/faixasCEPMicrozona/novo")
        public ModelAndView faixasCEPMicrozonaNovo() {            
            ModelAndView modelAndView = new ModelAndView("FaixasCEPMicrozonaFormulario");
            modelAndView.addObject("faixasCEPMicrozona", new FaixasCEPMicrozona());
            
            return modelAndView;
        }            
        
}
