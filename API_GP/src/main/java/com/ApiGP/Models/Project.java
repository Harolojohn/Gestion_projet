package com.ApiGP.Models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonIgnoreProperties(value = { "premiere_connexion", "password" }, allowSetters = true)
@JsonPropertyOrder({ "id", "nom", "budget", "taux_horaire", "user" })
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "budget")
    private int budget;

    @Column(name = "taux_horaire")
    private int taux_horaire;

    @Column(name = "date_creation")
    private Timestamp date_creation;

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_create;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
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
	return user_create;
    }

    public void setUser(User user) {
	this.user_create = user;
    }

    public Timestamp getDate_creation() {
	return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
	this.date_creation = date_creation;
    }

    public User getUser_create() {
	return user_create;
    }

    public void setUser_create(User user_create) {
	this.user_create = user_create;
    }

}
