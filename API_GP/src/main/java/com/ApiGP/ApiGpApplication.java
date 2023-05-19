package com.ApiGP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableAspectJAutoProxy
@OpenAPIDefinition(info = @Info(title = "API-GP", version = "beta", description = "API PERMETTANT LA GESTION DES PROJETS "))
public class ApiGpApplication {

    public static void main(String[] args) {
	SpringApplication.run(ApiGpApplication.class, args);
    }

}
