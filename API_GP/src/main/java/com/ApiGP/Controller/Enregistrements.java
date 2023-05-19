package com.ApiGP.Controller;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiGP.Models.Enregistrement;
import com.ApiGP.Models.EnregistrementCredentials;
import com.ApiGP.Models.UpdateEnregistrementCredentials;
import com.ApiGP.Responses.AnotherResponse;
import com.ApiGP.Responses.ResponseSuccessEnregistrement;
import com.ApiGP.Responses.ResponseSuccessListEnregistrement;
import com.ApiGP.Service.EnregistrementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Enregistrement")
public class Enregistrements {

    @Autowired
    private EnregistrementService enregistrementService;

    @Operation(summary = "Creer un enregistrement")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "enregistrement cree avec succes", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessEnregistrement.class)) }),
	    @ApiResponse(responseCode = "403", description = "le project ou le user n'existe pas dans la base de donnees", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }), })
    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody @Valid EnregistrementCredentials enregistrement) {

	Enregistrement enregsitrementSaved = enregistrementService.Create_enregsitrement(enregistrement);

	if (!Objects.isNull(enregsitrementSaved)) {

	    ResponseSuccessEnregistrement response_success = new ResponseSuccessEnregistrement("201",
		    "l'enregistrement a ete cree avec succes", enregsitrementSaved);

	    return new ResponseEntity<>(response_success, HttpStatus.CREATED);

	} else {

	    AnotherResponse response_conflict = new AnotherResponse("403",
		    "le project ou le user n'existe pas dans la base de donnees");

	    return new ResponseEntity<>(response_conflict, HttpStatus.NOT_FOUND);
	}
    }

    @Operation(summary = "modifier un enregistrement")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "enregistrement modifie avec succes", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessEnregistrement.class)) }),
	    @ApiResponse(responseCode = "403", description = "le project ou le user n'existe pas dans la base de donnees", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }), })
    @PutMapping("/")
    public ResponseEntity<Object> modifier(@RequestBody @Valid UpdateEnregistrementCredentials enregistrement) {

	Enregistrement enregsitrementUpdate = enregistrementService.update_enregistrement(enregistrement);

	System.out.println(enregsitrementUpdate.getId_enregistrement());

	if (enregsitrementUpdate.getId_enregistrement() == 0) {

	    AnotherResponse response_conflict = new AnotherResponse("404", "le enregistrement n'existe pas en BD");

	    return new ResponseEntity<>(response_conflict, HttpStatus.NOT_FOUND);

	} else {
	    ResponseSuccessEnregistrement reponse_correct = new ResponseSuccessEnregistrement("200",
		    "enregistrement modifie avec succes", enregsitrementUpdate);

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);
	}
    }

    @Operation(summary = "Liste de tous les enregistrements")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "liste de tous les enregistrements", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessListEnregistrement.class)) }), })
    @GetMapping("")
    public ResponseEntity<Object> get_allenregistrements() {

	Iterable<Enregistrement> list_enregistrements = enregistrementService.get_allenregistrements();

	ResponseSuccessListEnregistrement reponse_correct = new ResponseSuccessListEnregistrement("200",
		"liste de tous les enregistrements", list_enregistrements);

	return new ResponseEntity<>(reponse_correct, HttpStatus.OK);

    }

    @Operation(summary = "Detail d'un enregistrement")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Detail du project", content = {
	    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessEnregistrement.class)) }), })
    @GetMapping("/{id_enregistrement}")
    public ResponseEntity<Object> get_project_by_id(@PathVariable @Valid int id_enregistrement) {

	Optional<Enregistrement> enregistrement = enregistrementService.get_allenregistrements_by_id(id_enregistrement);

	if (enregistrement.isEmpty()) {

	    AnotherResponse reponse_correct = new AnotherResponse("200", "Project inexistant");

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);

	} else {
	    ResponseSuccessEnregistrement reponse_correct = new ResponseSuccessEnregistrement("200",
		    "Detail du project", enregistrement.get());

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);

	}

    }

    @Operation(summary = "Liste de tous les enregistrements d'un projet specifique")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "liste de tous les enregistrements d'un project", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessListEnregistrement.class)) }),
	    @ApiResponse(responseCode = "404", description = "le project n'existe pas", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessListEnregistrement.class)) }), })
    @GetMapping("/Project/{id_project}")
    public ResponseEntity<Object> get_allenregistrements_by_project(@PathVariable @Valid int id_project) {
	System.out.println("oui");
	Iterable<Enregistrement> list_enregistrements = enregistrementService
		.get_allenregistrements_by_project(id_project);

	if (list_enregistrements == null) {

	    AnotherResponse response_not_found = new AnotherResponse("404", "project inexistant");

	    return new ResponseEntity<>(response_not_found, HttpStatus.NOT_FOUND);

	} else {

	    ResponseSuccessListEnregistrement reponse_correct = new ResponseSuccessListEnregistrement("200",
		    "liste de tous les enregistrements", list_enregistrements);

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);
	}

    }

    @Operation(summary = "Liste de tous les enregistrements d'un consultant specifique")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "liste de tous les enregistrements d'un project", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessListEnregistrement.class)) }),
	    @ApiResponse(responseCode = "404", description = "Le user n'existe pas", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessListEnregistrement.class)) }), })
    @GetMapping("/User/{id_user}")
    public ResponseEntity<Object> get_allenregistrements_by_user(@PathVariable @Valid int id_user) {

	Iterable<Enregistrement> list_enregistrements = enregistrementService.get_allenregistrements_by_user(id_user);

	if (list_enregistrements == null) {

	    AnotherResponse response_not_found = new AnotherResponse("404", "user inexistant");

	    return new ResponseEntity<>(response_not_found, HttpStatus.NOT_FOUND);
	} else {

	    ResponseSuccessListEnregistrement reponse_correct = new ResponseSuccessListEnregistrement("200",
		    "liste de tous les enregistrements", list_enregistrements);

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);

	}

    }
}
