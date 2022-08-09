package br.com.henrique;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NextLevelJavaV2ApplicationTests {

//    @Autowired
//    private TestRestTemplate testRestTemplate;

    @Test
    public void testApplication() throws URISyntaxException { 
//        Integer codigoEmpresa = 21;
//        
//        ResponseEntity<MyResponseObject> response = testRestTemplate.getForEntity("/empresa/{codigo}", MyResponseObject, codigoEmpresa);
//        
//        assertThat(response.getStatusCode()).isEqualTo(200);
//        
//        MyResponseObject  body = response.getBody();
        
        // then do what you want
    }
}