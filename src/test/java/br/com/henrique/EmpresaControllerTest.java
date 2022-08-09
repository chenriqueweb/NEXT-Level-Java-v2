package br.com.henrique;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.henrique.model.Empresa;
import br.com.henrique.service.EmpresaService;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class EmpresaControllerTest {
	
	
    @Autowired
    private TestRestTemplate restTemplate;
    private EmpresaService empresaService;
     
    @Test
    public void testAddEmployeeSuccess() throws URISyntaxException, ParseException 
    {
        Integer codigoEmpresa = 21;
        final String baseUrl = "http://localhost:8080"  + "/empresa/" + codigoEmpresa;
        URI uri = new URI(baseUrl);        
        
  	    final SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
  	    Empresa empresa0001 = new Empresa(21, "VIAVAREJO", "33041260094711", formatterDate.parse("25/08/2016"));
  	    
        System.out.println(empresa0001.getCodigo());
        System.out.println(empresa0001.getRaizCNPJ());
        System.out.println(empresa0001.getRazaoSocial());
        System.out.println(empresa0001.getDataAbertura());
    	
    	empresaService.addEmpresa(empresa0001);	
        
    	// Busca pelo endereço e methodo POST
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Empresa> request = new HttpEntity<>(empresa0001, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        Assert.assertEquals(201, result.getStatusCodeValue());
    }
	
//	private MockMvc mockMvc;
//	
//    @Mock
//    private EmpresaService empresaService;
//    
//    @InjectMocks
//    private EmpresaController empresaController;
//    private EmpresaRepository repositEmpresa;
//    
//    @SuppressWarnings("deprecation")
//	@Before
//    public void setup() {
//    	MockitoAnnotations.initMocks(empresaController);
//    	
//    	repositEmpresa = Mockito.mock(EmpresaRepository.class);
//    	
//    	mockMvc = MockMvcBuilders.standaloneSetup(empresaController).build();
//    }
//	
//	@Test
//	public void testEmpresafindById() throws Exception {  // URISyntaxException 
//		
//  		RestTemplate restTemplate = new RestTemplate();
//  		
//      	final SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
//      	
//      	Empresa empresa0001 = new Empresa(21, "VIAVAREJO", "33041260094711", formatterDate.parse("25/08/2016"));
//      	
//        System.out.println(empresa0001.getCodigo());
//        System.out.println(empresa0001.getRaizCNPJ());
//        System.out.println(empresa0001.getRazaoSocial());
//        System.out.println(empresa0001.getDataAbertura());
//      	
//      	empresaService.addEmpresa(empresa0001);		
//  		
//    	
//// Test Busca por ID		
//		
//		Integer codigoEmpresa = 21;
//		
//		final String baseUrl = "http://localhost:8080"  + "/empresa/" + codigoEmpresa;
//	    URI uri = new URI(baseUrl);
//	    
//	    HttpEntity<Empresa> request = new HttpEntity<>(empresa0001);
//	    
//	    // ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);  // GET
//	    ResponseEntity<String> result = restTemplate.postForLocation(uri, request, String.class);  // POST
//	     
//	    // Verificando retorno - 200 é sucesso
//	    Assert.assertEquals(200, result.getStatusCodeValue());
//	    Assert.assertEquals(true, result.getBody().contains("empresa"));
//	    
//  	}
	
	
//    @Mock
//    private EmpresaService empresaService;
//    
//	@InjectMocks
//	private EmpresaController empresaController;
//    private EmpresaRepository repositEmpresa;
//	
//    @BeforeEach
//    void setup() {
//    	repositEmpresa = Mockito.mock(EmpresaRepository.class);
//    }    
//    
//    @Test
//    public void testEmpresa() throws Exception {
//    	final SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
//    	
//    	Empresa empresa0001 = new Empresa(22, "VIAVAREJO", "33041260094711", formatterDate.parse("25/08/2016"));
//    	
//        System.out.println(empresa0001.getCodigo());
//        System.out.println(empresa0001.getRaizCNPJ());
//        System.out.println(empresa0001.getRazaoSocial());
//        System.out.println(empresa0001.getDataAbertura());
//    	
//    	empresaService.addEmpresa(empresa0001);
//    	
//    	verify(empresaService, times(1)).addEmpresa(empresa0001); 
//    }

//	@Test
//	void testEmpresa() throws Exception {
//        final SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");      
//        
//        // Carga da Tabela: EMPRESA
//        Empresa empresa0001 = new Empresa(22, "VIAVAREJO", "33041260094711", formatterDate.parse("25/08/2016"));
//        
//        System.out.println(empresa0001.getCodigo());
//        System.out.println(empresa0001.getRaizCNPJ());
//        System.out.println(empresa0001.getRazaoSocial());
//        System.out.println(empresa0001.getDataAbertura());
//        
//        assertEquals(22, empresaService.addEmpresa(empresa0001).getCodigo());		
//        
//        // assertNull(empresa0001.getCodigo());
//		
//		Integer codigoEsperado = 21;
//		
//		// Integer codigoRetornado = repositEmpresa.findById(codigoEsperado).get().getCodigo();
//		
//		Empresa empresaRetornoTest = repositEmpresaTest.findById(codigoEsperado).orElse(null);
//		
//		Integer codigoRetornado = empresaRetornoTest.getCodigo();
//		
//		assertEquals(codigoEsperado, codigoRetornado, 0);		
//	}

}
