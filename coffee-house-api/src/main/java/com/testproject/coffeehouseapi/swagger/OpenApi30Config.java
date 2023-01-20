package com.testproject.coffeehouseapi.swagger;

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
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApi30Config {

    @Value("${api.title}")
    private String API_TITLE;
    @Value("${api.version}")
    private String API_VERSION;
    @Value("${api.description}")
    private String API_DESCRIPTION;
    @Value("${api.url}")
    private String API_URL;

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info().title(API_TITLE)
                        .version(API_VERSION)
                        .description(API_DESCRIPTION))
                .servers(List.of(new Server().url(API_URL)
                        .description("Dev service")));
    }
}
