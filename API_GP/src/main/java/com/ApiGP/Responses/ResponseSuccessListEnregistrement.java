package com.ApiGP.Responses;

import java.util.LinkedList;
import java.util.List;

import com.ApiGP.Models.Enregistrement;

public class ResponseSuccessListEnregistrement {

    private String code;

    private String message;

    private Iterable<ContentEnregistrement> contentEnregistrement;

    public ResponseSuccessListEnregistrement(String code, String message,
	    Iterable<Enregistrement> list_enregistrement) {

	this.code = code;
	this.message = message;

	List list_simplifiee_enregistrements = new LinkedList();

	for (Enregistrement enregistrement : list_enregistrement) {

	    ContentEnregistrement contentEnregistrementent = new ContentEnregistrement(enregistrement);

	    list_simplifiee_enregistrements.add(contentEnregistrementent);
	}

	this.contentEnregistrement = list_simplifiee_enregistrements;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Iterable<ContentEnregistrement> getContentEnregistrement() {
	return contentEnregistrement;
    }

    public void setContentEnregistrement(Iterable<ContentEnregistrement> contentEnregistrement) {
	this.contentEnregistrement = contentEnregistrement;
    }

}
