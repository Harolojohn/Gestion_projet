package com.ApiGP.Responses;

import com.ApiGP.Models.Project;

public class ResponseSuccessProject {

    private String code;

    private String message;

    private ContentProject contentProject;

    public ResponseSuccessProject(String code, String message, Project project) {

	this.code = code;
	this.message = message;

	ContentProject contentProject = new ContentProject(project.getId(), project.getNom(), project.getBudget(),
		project.getTaux_horaire(), project.getUser());

	this.contentProject = contentProject;
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

    public ContentProject getContentProject() {
	return contentProject;
    }

    public void setContentProject(ContentProject contentProject) {
	this.contentProject = contentProject;
    }

}
