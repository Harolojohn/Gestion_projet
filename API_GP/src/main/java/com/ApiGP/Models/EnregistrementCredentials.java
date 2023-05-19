package com.ApiGP.Models;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class EnregistrementCredentials {

//    @NotBlank
    // @NotEmpty
    @NotNull
    private int id_user;

//    @NotBlank
    // @NotEmpty
    @NotNull
    private int id_project;

    // @NotBlank
    // @NotEmpty
    @NotNull
    private Timestamp date_depart;

    // @NotBlank
    // @NotEmpty
    @NotNull
    private Timestamp date_fin;

    public int getId_user() {
	return id_user;
    }

    public void setId_user(int id_user) {
	this.id_user = id_user;
    }

    public int getId_project() {
	return id_project;
    }

    public void setId_project(int id_project) {
	this.id_project = id_project;
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

}
