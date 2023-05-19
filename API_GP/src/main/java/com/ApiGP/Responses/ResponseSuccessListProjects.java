package com.ApiGP.Responses;

import java.util.LinkedList;
import java.util.List;

import com.ApiGP.Models.Project;

public class ResponseSuccessListProjects {

    private String code;

    private String message;

    private Iterable<ContentProject> contentProject;

    public ResponseSuccessListProjects(String code, String message, Iterable<Project> list_projects) {

	this.code = code;
	this.message = message;

	List list_simplifiee_projects = new LinkedList();

	for (Project project : list_projects) {

	    ContentProject content_project = new ContentProject(project.getId(), project.getNom(), project.getBudget(),
		    project.getTaux_horaire(), project.getUser());

	    list_simplifiee_projects.add(content_project);
	}

	this.contentProject = list_simplifiee_projects;
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

    public Iterable<ContentProject> getContentProject() {
	return contentProject;
    }

    public void setContentProject(Iterable<ContentProject> contentProject) {
	this.contentProject = contentProject;
    }

}
