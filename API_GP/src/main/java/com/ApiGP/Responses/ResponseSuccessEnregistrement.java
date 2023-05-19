package com.ApiGP.Responses;

import com.ApiGP.Models.Enregistrement;

public class ResponseSuccessEnregistrement {

    private String code;

    private String message;

    private ContentEnregistrement contentEnregistrement;

    public ResponseSuccessEnregistrement(String code, String message, Enregistrement enregistrement) {

	this.code = code;
	this.message = message;

	ContentEnregistrement contentEnregistrement = new ContentEnregistrement(enregistrement);

	this.contentEnregistrement = contentEnregistrement;
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

    public ContentEnregistrement getContentEnregistrement() {
	return contentEnregistrement;
    }

    public void setContentEnregistrement(ContentEnregistrement contentEnregistrement) {
	this.contentEnregistrement = contentEnregistrement;
    }

}
