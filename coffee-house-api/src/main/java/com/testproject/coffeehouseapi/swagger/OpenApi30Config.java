package com.testproject.coffeehouseapi.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "Authorization",
        description = "Bearer Authentication with JWT Token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenApi30Config {

    @Value("${api.title}")
    private String API_TITLE;
    @Value("${api.version}")
    private String API_VERSION;
    @Value("${api.description}")
    private String API_DESCRIPTION;
    @Value("${dev.server}")
    private String DEV_SERVER;
    @Value("${prod.server}")
    private String PROD_SERVER;

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info().title(API_TITLE)
                        .version(API_VERSION)
                        .description(API_DESCRIPTION))
                .servers(List.of(new Server().url(DEV_SERVER)
                        .description("development"),
                        new Server().url(PROD_SERVER)
                                .description("production")));
    }
}
