package com.ApiGP.Responses;

import java.sql.Timestamp;

import com.ApiGP.Models.Enregistrement;
import com.ApiGP.Models.Project;
import com.ApiGP.Models.User;

public class ContentEnregistrement {

    private Integer id_enregistrement;

    private User user;

    private Project project;

    private Timestamp date_depart;

    private Timestamp date_fin;

    private Timestamp date_creation;

    public ContentEnregistrement(Enregistrement enregistrement) {
	this.id_enregistrement = enregistrement.getId_enregistrement();
	this.user = enregistrement.getUser();
	this.project = enregistrement.getProject();
	this.date_depart = enregistrement.getDate_depart();
	this.date_fin = enregistrement.getDate_fin();
	this.date_creation = enregistrement.getDate_creation();
    }

    public ContentEnregistrement() {
	// TODO Auto-generated constructor stub
    }

    public Integer getId_enregistrement() {
	return id_enregistrement;
    }

    public void setId_enregistrement(Integer id_enregistrement) {
	this.id_enregistrement = id_enregistrement;
    }

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

    public Timestamp getDate_creation() {
	return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
	this.date_creation = date_creation;
    }

}
