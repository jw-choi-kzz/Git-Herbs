package com.happiness.githerbs.global.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Value("${swagger.local}")
	private String localUrl;
	@Value("${swagger.production}")
	private String productionUrl;

	@Bean
	public OpenAPI openAPI(){

		SecurityScheme accessTokenSecurityScheme = new SecurityScheme()
			.type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")
			.in(SecurityScheme.In.HEADER).name("Authorization");
		SecurityScheme refreshTokenSecurityScheme = new SecurityScheme()
			.type(SecurityScheme.Type.APIKEY)
			.in(SecurityScheme.In.HEADER).name("Refresh-Token");


		SecurityRequirement securityRequirement = new SecurityRequirement().addList("Access Token (Bearer)").addList("Refresh Token");

		Components components = new Components()
			.addSecuritySchemes("Access Token (Bearer)", accessTokenSecurityScheme)
			.addSecuritySchemes("Refresh Token", refreshTokenSecurityScheme);

		return new OpenAPI()
			.info(new Info()
				.title("GitHerbs API")
				.description("GitHerbs API Documentation")
				.version("0.1.1"))
			.components(components)
			.servers(List.of(
				new Server().url(productionUrl),
				new Server().url(localUrl)

			))
			.addSecurityItem(securityRequirement);
	}

}

