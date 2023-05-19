package com.ApiGP.Responses;

import com.ApiGP.Models.User;

public class ResponseSuccessUsers {

    private String code;

    private String message;

    private Content content;

    public ResponseSuccessUsers(String code, String message, User user) {

	this.code = code;
	this.message = message;

	Content content = new Content(user.getId(), user.getNom(), user.getEmail(), user.getRole());

	this.content = content;
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

    public Content getContent() {
	return content;
    }

    public void setContent(Content content) {
	this.content = content;
    }

}
