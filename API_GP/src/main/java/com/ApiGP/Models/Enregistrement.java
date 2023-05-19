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
@JsonPropertyOrder({ "id_enregistrement", "date_depart", "date_fin", "date_creation", "user", "project" })
@Table(name = "enregistrements")
public class Enregistrement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enregistrement")
    private int id_enregistrement;

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, })
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @Column(name = "date_depart")
    private Timestamp date_depart;

    @Column(name = "date_fin")
    private Timestamp date_fin;

    @Column(name = "date_creation")
    private Timestamp date_creation;

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Project getProject() {
	return project;
    }

    public void setProject(Project project) {
	this.project = project;
    }

    public Timestamp getDate_depart() {
	return date_depart;
    }

    public void setDate_depart(Timestamp date_depart) {
	this.date_depart = date_depart;
    }

    public Timestamp getDate_fin() {
	return date_fin;
    }

    public void setDate_fin(Timestamp date_fin) {
	this.date_fin = date_fin;
    }

    public int getId_enregistrement() {
	return id_enregistrement;
    }

    public void setId_enregistrement(int id_enregistrement) {
	this.id_enregistrement = id_enregistrement;
    }

    public Timestamp getDate_creation() {
	return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
	this.date_creation = date_creation;
    }

}
