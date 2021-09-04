package com.cybertek.businessmansystem_api.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI costumOpenApi() {
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setType(SecurityScheme.Type.HTTP);
        securityScheme.setScheme("bearer");
        securityScheme.setBearerFormat("JWT");
        securityScheme.setIn(SecurityScheme.In.HEADER);
        securityScheme.setName("Authorization");
        Info infoVersion = new Info().title("Cybertek Ticketing Application").version("snapshot");
        SecurityRequirement securityItem = new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write"));

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt", securityScheme))
                .info(infoVersion)
                .addSecurityItem(securityItem);
    }

}
