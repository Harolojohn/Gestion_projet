package com.ApiGP.Responses;

import java.util.LinkedList;
import java.util.List;

import com.ApiGP.Models.User;

public class ResponseSuccessList {

    private String code;

    private String message;

    private Iterable<Content> content;

    public ResponseSuccessList(String code, String message, Iterable<User> list_users) {

	this.code = code;
	this.message = message;

	List list_simplifiee_users = new LinkedList();

	for (User user : list_users) {

	    if (user.getRole().equals("consultant")) {

		Content content_user = new Content(user.getId(), user.getEmail(), user.getNom(), user.getRole());

		list_simplifiee_users.add(content_user);
	    }
	}

	this.content = list_simplifiee_users;
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

    public Iterable<Content> getContent() {
	return content;
    }

    public void setContent(Iterable<Content> content) {
	this.content = content;
    }

}
