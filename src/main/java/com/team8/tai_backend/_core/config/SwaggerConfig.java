package com.team8.tai_backend._core.config;

import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springdoc-public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("https://taijava.misajd.dev").description("Generated server url"))
                .addServersItem(new Server().url("http://localhost").description("local server url"))
                .info(new Info()
                        .title("Book Everywhere API")
                        .version("v1")
                        .description("읽는곳곳 API 명세서"));
    }
}
