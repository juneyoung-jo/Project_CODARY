package com.spring.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;

import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

//   http://localhost:8000/codary/swagger-ui.html

   private String version = "V1";
   private String title = "Codary API " + version;
   
   @Bean
   public Docket api() {
      List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
      responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
      responseMessages.add(new ResponseMessageBuilder().code(500).message("Server Error !!!").responseModel(new ModelRef("Error")).build());
      responseMessages.add(new ResponseMessageBuilder().code(404).message("page not found !!!").build());
      return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName(version).select()
               .apis(RequestHandlerSelectors.basePackage("com.spring.web.controller"))
               .paths(postPaths()).build()
               .useDefaultResponseMessages(false)
               .globalResponseMessage(RequestMethod.GET,responseMessages);
   }
   
   private Predicate<String> postPaths() {
      return PathSelectors.any();
//      return or(regex("/user/.*"), regex("/article/.*"), regex("/memo/.*"));
//      return regex("/admin/.*");
   }

   private ApiInfo apiInfo() {
      return new ApiInfoBuilder().title(title)
            .description("<h3>Codary API Reference for Developers</h3><br>") 
            .version("1.0").build();

   }

}