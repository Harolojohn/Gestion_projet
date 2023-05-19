package com.ApiGP.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProjectCredentials {

    @NotBlank
    @NotEmpty
    @NotNull
    private String nom_projet;

//    @NotBlank
//    @NotEmpty
//    @NotNull
    private int budget;

    // @NotBlank
    // @NotEmpty
    // @NotNull
    private int taux_horaire;

    // @NotBlank
    // @NotEmpty
    @NotNull
    private int id_user;

    public String getNom_projet() {
	return nom_projet;
    }

    public void setNom_projet(String nom_projet) {
	this.nom_projet = nom_projet;
    }

    public int getBudget() {
	return budget;
    }

    public void setBudget(int budget) {
	this.budget = budget;
    }

    public int getTaux_horaire() {
	return taux_horaire;
    }

    public void setTaux_horaire(int taux_horaire) {
	this.taux_horaire = taux_horaire;
    }

    public int getId_user() {
	return id_user;
    }

    public void setId_user(int id_user) {
	this.id_user = id_user;
    }

}
