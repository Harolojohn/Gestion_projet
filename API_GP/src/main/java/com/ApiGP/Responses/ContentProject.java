package com.ApiGP.Responses;

import com.ApiGP.Models.User;

public class ContentProject {

    private Integer id;

    private String nom_projet;

    private int budget;

    private int taux_horaire;

    private User user;

    public ContentProject(int id, String nom_projet, int budget, int taux_horaire, User user) {
	this.id = id;
	this.nom_projet = nom_projet;
	this.budget = budget;
	this.taux_horaire = taux_horaire;
	this.user = user;
    }

    public ContentProject() {
	// TODO Auto-generated constructor stub
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

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

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

}
