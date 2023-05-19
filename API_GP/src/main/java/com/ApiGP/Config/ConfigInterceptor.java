package com.ApiGP.Config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ApiGP.Properties;
import com.ApiGP.Responses.AnotherResponse;

@Aspect
@Component
public class ConfigInterceptor {

    @Autowired
    private Properties properties;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Around("within(com.ApiGP.*.*))")
    public Object exception_interceptor(ProceedingJoinPoint proceedingJoinPoint) {

	Object value = null;

	try {

	    value = proceedingJoinPoint.proceed();

	} catch (Throwable e) {

	    e.printStackTrace();

	    AnotherResponse response_success = new AnotherResponse("200",
		    "un bug vient de se produire--contactez l'administrateur ");

	    return new ResponseEntity<>(response_success, HttpStatus.OK);
	}

	return value;
    }

    @Before("within(com.ApiGP.Controller.*))")
    public void token_interceptor() throws IOException {

	String authorizationHeader = request.getHeader("Authorization");

	if (authorizationHeader == null) {

	    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token manquant");
	}

	if (authorizationHeader != null) {

	    if (!authorizationHeader.equals(properties.getApi_key())) {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous n'avez pas la bonne clé");
	    }

	}

    }

}