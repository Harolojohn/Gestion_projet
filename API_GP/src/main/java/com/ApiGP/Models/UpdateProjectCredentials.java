package com.ApiGP.Models;

public class UpdateProjectCredentials {

    private Integer id;

    private String nom_projet;

    private int budget;

    private int taux_horaire;

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

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
