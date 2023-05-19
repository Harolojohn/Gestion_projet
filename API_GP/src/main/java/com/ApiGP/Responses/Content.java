package com.ApiGP.Responses;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "user")
public class Content {

    private Integer id;

    private String nom;

    private String email;

    private String role;

    public Content(int id, String nom, String email, String role) {
	this.id = id;
	this.nom = nom;
	this.email = email;
	this.role = role;
    }

    public Content() {
	// TODO Auto-generated constructor stub
    }

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

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
