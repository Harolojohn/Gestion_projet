package com.ApiGP.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User_Infos {

    @NotBlank
    @NotEmpty
    @NotNull
    private String nom;

    @NotBlank
    @NotEmpty
    @NotNull
    private String email;

    @NotBlank
    @NotEmpty
    @NotNull
    private String password;

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

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
