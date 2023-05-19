package com.ApiGP.Models;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class UpdateEnregistrementCredentials {

    @NotNull
    private int id_enregistrement;

    @NotNull
    private Timestamp date_depart;

    @NotNull
    private Timestamp date_fin;

    public int getId_enregistrement() {
	return id_enregistrement;
    }

    public void setId_enregistrement(int id_enregistrement) {
	this.id_enregistrement = id_enregistrement;
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
