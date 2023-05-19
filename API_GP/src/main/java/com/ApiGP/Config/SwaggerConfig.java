package com.ApiGP.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Component
public class SwaggerConfig {

    private final String moduleName;

    private final String apiVersion;

    public SwaggerConfig(

    ) {
	this.moduleName = "moduleName";
	this.apiVersion = "apiVersion";
    }

    @Bean
    public OpenAPI customOpenAPI() {
	final String securitySchemeName = "bearerAuth";
	final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
	return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
		.components(
			new Components().addSecuritySchemes(securitySchemeName,
				new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP)
					.scheme("bearer").bearerFormat("JWT")))
		.info(new Info().title(apiTitle).version(apiVersion));
    }
}
