package br.com.henrique.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

   @Bean
   public Docket api() {
       return new Docket(DocumentationType.SWAGGER_2)
                         .select()
                         .apis(RequestHandlerSelectors.basePackage("br.com.henrique.controller"))
                         .paths(Predicates.not(PathSelectors.regex("/atende/filial.*")))
                         .paths(Predicates.not(PathSelectors.regex("/atende/range*.*")))
                         .paths(Predicates.not(PathSelectors.regex("/empresa/remover.*")))
                         .paths(Predicates.not(PathSelectors.regex("/empresa/editar.*")))
                         .paths(Predicates.not(PathSelectors.regex("/empresa/page.*")))
                         .paths(Predicates.not(PathSelectors.regex("/estado/remover.*")))
                         .paths(Predicates.not(PathSelectors.regex("/estado/editar.*")))
                         .paths(Predicates.not(PathSelectors.regex("/estado/page.*")))         
                         .paths(Predicates.not(PathSelectors.regex("/faixasCEPMicrozona/page.*")))    
                         .paths(Predicates.not(PathSelectors.regex("/filial/remover.*")))
                         .paths(Predicates.not(PathSelectors.regex("/filial/editar.*")))
                         .paths(Predicates.not(PathSelectors.regex("/filial/page.*")))        
                         .paths(Predicates.not(PathSelectors.regex("/microzona/page.*")))      
                         .paths(Predicates.not(PathSelectors.regex("/municipio/editar.*")))
                         .paths(Predicates.not(PathSelectors.regex("/municipio/page.*")))  
                         .paths(Predicates.not(PathSelectors.regex("/rotaEntrega/remover.*")))
                         .paths(Predicates.not(PathSelectors.regex("/rotaEntrega/editar.*")))
                         .paths(Predicates.not(PathSelectors.regex("/rotaEntrega/page.*")))                            
                         .paths(PathSelectors.any())                         
                         .build()
                         .apiInfo(apiInfo())
                         .useDefaultResponseMessages(false);
   }

   private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        		                 .title("Next Level Java - Spring Boot REST API")
        		                 .description("\"Projeto REST API em Java com Spring Boot\"")
        	                     .version("2.0.0")
        	                     .contact(new Contact("Autor: Carlos Henrique",
        	                    		              "http://www.nextlevel.henrique.com.br:8080", 
        	                    		              "carlos.henrique.mobile@gmail.com"))
        	                     .build();
   }
   
   @Override
   protected void addResourceHandlers(ResourceHandlerRegistry registry) {
              registry.addResourceHandler("swagger-ui.html")
                      .addResourceLocations("classpath:/META-INF/resources/");

              registry.addResourceHandler("/webjars/**")
                      .addResourceLocations("classpath:/META-INF/resources/webjars/");
   }   
   
   @Override
   public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
       argumentResolvers.add( new PageableHandlerMethodArgumentResolver());
   }
}