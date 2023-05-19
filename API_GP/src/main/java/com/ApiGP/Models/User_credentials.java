package com.ApiGP.Models;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User_credentials {

    @Column(name = "email")
    @NotEmpty(message = "L'email ne peux pas etre vide")
    @NotBlank(message = "L'email ne peux pas etre vide")
    @NotNull(message = "L'email ne peux pas etre vide")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Le password ne peux pas etre vide")
    @NotBlank(message = "Le password ne peux pas etre vide")
    @NotNull(message = "Le password ne peux pas etre vide")
    private String password;

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
