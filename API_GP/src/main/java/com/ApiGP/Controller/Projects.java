package com.ApiGP.Controller;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiGP.Models.Project;
import com.ApiGP.Models.ProjectCredentials;
import com.ApiGP.Models.UpdateProjectCredentials;
import com.ApiGP.Responses.AnotherResponse;
import com.ApiGP.Responses.ResponseSuccessListProjects;
import com.ApiGP.Responses.ResponseSuccessProject;
import com.ApiGP.Service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Project")
public class Projects {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Creer un project")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "le project a ete cree avec succes", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessProject.class)) }),
	    @ApiResponse(responseCode = "200", description = "Vous devez choisir un autre nom", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }),
	    @ApiResponse(responseCode = "401", description = "Vous n'etes pas autorise a effctuer cette action", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }),
	    @ApiResponse(responseCode = "403", description = "Le user n'existe pas", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }), })
    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody @Valid ProjectCredentials project) {

	Project projectSaved = new Project();

	projectSaved = projectService.Create_project(project);

	if (projectSaved.getNom().equals("#Rename")) {

	    AnotherResponse response_conflict = new AnotherResponse("200", "Vous devez choisir un autre nom");

	    return new ResponseEntity<>(response_conflict, HttpStatus.OK);

	} else if (projectSaved.getNom().equals("#Unauthorized")) {

	    AnotherResponse response_unauthorized = new AnotherResponse("401",
		    "Vous n'etes pas autorise a effctuer cette action");

	    return new ResponseEntity<>(response_unauthorized, HttpStatus.UNAUTHORIZED);
	} else if (projectSaved.getNom().equals("#Not Found")) {
	    AnotherResponse response_not_found = new AnotherResponse("403", "Le user n'existe pas");

	    return new ResponseEntity<>(response_not_found, HttpStatus.NOT_FOUND);
	} else {

	    ResponseSuccessProject response_success = new ResponseSuccessProject("201",
		    "l'utilisateur a ete cree avec succes", projectSaved);

	    return new ResponseEntity<>(response_success, HttpStatus.CREATED);

	}
    }

    @Operation(summary = "Liste de tous les projects")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "liste de tous les projects", content = {
	    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessProject.class)) }), })
    @GetMapping("")
    public ResponseEntity<Object> get_allprojects() {

	Iterable<Project> list_projects = projectService.get_allprojects();

	ResponseSuccessListProjects reponse_correct = new ResponseSuccessListProjects("200",
		"liste de tous les projects", list_projects);

	return new ResponseEntity<>(reponse_correct, HttpStatus.OK);

    }

    @Operation(summary = "Detail d'un project")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Detail du project", content = {
	    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessProject.class)) }), })
    @GetMapping("/{id_project}")
    public ResponseEntity<Object> get_project_by_id(@PathVariable @Valid int id_project) {

	Optional<Project> project = projectService.get_project_by_id(id_project);

	if (project.isEmpty()) {

	    AnotherResponse reponse_correct = new AnotherResponse("200", "project inexistant");

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);
	} else {

	    ResponseSuccessProject reponse_correct = new ResponseSuccessProject("200", "Detail du project",
		    project.get());

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);
	}

    }

    @Operation(summary = "Modifier un project")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "le project a ete modifie avec succes", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessProject.class)) }),
	    @ApiResponse(responseCode = "403", description = "le project n'existe pas", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }), })
    @PutMapping("")
    public ResponseEntity<Object> update_project(@RequestBody @Valid UpdateProjectCredentials project) {

	Project projectUpdate = projectService.update_project(project);

	if (Objects.isNull(projectUpdate.getId())) {

	    AnotherResponse response_conflict = new AnotherResponse("403", "le project n'existe pas en BD");

	    return new ResponseEntity<>(response_conflict, HttpStatus.NOT_FOUND);

	} else {
	    ResponseSuccessProject reponse_correct = new ResponseSuccessProject("200", "project modifie avec succes",
		    projectUpdate);

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);
	}

    }

    @Operation(summary = "Supprimer un project")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "le project a ete supprime avec succes", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessProject.class)) }),
	    @ApiResponse(responseCode = "403", description = "le project n'existe pas", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }), })
    @DeleteMapping("/{id_project}")
    public ResponseEntity<Object> delete_project(@PathVariable @Valid int id_project) {

	int status = projectService.delete_project_by_id(id_project);

	if (status == 1) {

	    AnotherResponse reponse_correct = new AnotherResponse("200", "project supprime avec succes");

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);
	} else {

	    AnotherResponse response_conflict = new AnotherResponse("403", "le project n'existe pas en BD");

	    return new ResponseEntity<>(response_conflict, HttpStatus.NOT_FOUND);
	}
    }

}
