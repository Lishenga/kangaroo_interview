package com.kangaroo.interview.v1.configs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Setter
@Getter
@ToString
@Configuration
@ConfigurationProperties(prefix = "system-config")
public class ApplicationConfig {
    
    private TokenGeneratorConfig generator;

    private Api api;

    @Setter @Getter @ToString
    public static class TokenGeneratorConfig  {

        private String key;
    }

    @Setter @Getter @ToString
    public static class Api {

        private String version;
    }
    
    @Bean
	public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearer";
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("JWT",
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                                .in(SecurityScheme.In.HEADER)
                                .bearerFormat("JWT")))
				.info(new Info().title("Authentication and Authorization API").version("1.0.0").description(
						"This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).For this sample, you can use the api key `special-key` to test the authorization     filters.")
						.termsOfService("http://Lanthanion.com")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}