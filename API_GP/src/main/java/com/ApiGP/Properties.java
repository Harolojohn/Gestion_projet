package com.ApiGP;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "settings")
public class Properties {

    private String api_key;

    public String getApi_key() {
	return api_key;
    }

    public void setApi_key(String api_key) {
	this.api_key = api_key;
    }

}
