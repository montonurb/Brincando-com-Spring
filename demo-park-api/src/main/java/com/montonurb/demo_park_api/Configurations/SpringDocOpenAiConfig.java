package com.montonurb.demo_park_api.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocOpenAiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("REST API - Spring Park")
                    .description("API para gestão de estacionamento")
                    .version("v1")
                    .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                    .contact(new Contact().name("Bruno Monteiro").email("brunomoncar007@gmail.com"))
            );
    }
}
