package com.ApiGP.Controller;

import java.io.IOException;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiGP.Models.Code;
import com.ApiGP.Models.Email;
import com.ApiGP.Models.User;
import com.ApiGP.Models.User_Infos;
import com.ApiGP.Models.User_credentials;
import com.ApiGP.Repository.UserRepository;
import com.ApiGP.Responses.AnotherResponse;
import com.ApiGP.Responses.ResponseSuccessList;
import com.ApiGP.Responses.ResponseSuccessUsers;
import com.ApiGP.Service.Userservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/User")
public class Users {

    @Autowired
    private Userservice Uservice;

    @Autowired
    private UserRepository userRepository;

    Integer code_envoye = null;

    @Operation(summary = "Creer un consultant")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "utilisateur cree avec succes", content = {
	    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessUsers.class)) }),
	    @ApiResponse(responseCode = "409", description = "l'email existe deja dans la base de donnees", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }), })
    @PostMapping("/consultant")
    public ResponseEntity<Object> create(@RequestBody @Valid User_Infos user) {

	User UserSaved;

	// Miniscules avec la premeire lettre en Maj

	user.setNom(user.getNom().toLowerCase());
	char[] nom = user.getNom().toCharArray();
	nom[0] = Character.toUpperCase(nom[0]);
	user.setNom(new String(nom));

	UserSaved = Uservice.Create_consultant(user);

	if (Objects.isNull(UserSaved.getId())) {

	    AnotherResponse response_conflict = new AnotherResponse("409",
		    "l'email existe deja dans la Base de donnees");

	    return new ResponseEntity<>(response_conflict, HttpStatus.CONFLICT);

	} else {

	    ResponseSuccessUsers response_success = new ResponseSuccessUsers("201",
		    "le consultant a ete cree avec succes", UserSaved);

	    return new ResponseEntity<>(response_success, HttpStatus.CREATED);

	}
    }

    @PostMapping("/send_code")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "le code a ete correctement envoye", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }),
	    @ApiResponse(responseCode = "404", description = "l'email entre n'existe pas", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }), })
    @Operation(summary = "Envoyer un code par email")
    public ResponseEntity<Object> send_code(@RequestBody @Valid Email email) throws MessagingException {

	code_envoye = Uservice.sendCode(email.getEmail());

	if (code_envoye == 0) {

	    code_envoye = null;

	    AnotherResponse reponse_not_found = new AnotherResponse("404", "L'email entre est introuvable");

	    return new ResponseEntity<>(reponse_not_found, HttpStatus.NOT_FOUND);

	} else {

	    AnotherResponse response_success = new AnotherResponse("200", "le code a ete correctement envoye");

	    return new ResponseEntity<>(response_success, HttpStatus.OK);

	}
    }

    @PostMapping("/validate_code")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "le code est correct", content = {
	    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }),
	    @ApiResponse(responseCode = "401", description = "le code est incorrect", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }) })
    @Operation(summary = "Validation du code envoye")
    public ResponseEntity<Object> validate_code(@RequestBody @Valid Code code_saisi, HttpServletRequest request)
	    throws MessagingException {

	if (code_saisi.getCode() != code_envoye) {

	    AnotherResponse reponse_incorrect = new AnotherResponse("401", "Code incorrect");

	    return new ResponseEntity<>(reponse_incorrect, HttpStatus.UNAUTHORIZED);

	} else {

	    AnotherResponse reponse_correct = new AnotherResponse("200", "Code correct");

	    return new ResponseEntity<>(reponse_correct, HttpStatus.OK);
	}

    }

    @PostMapping("/connexion")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Connexion reussie pour le consultant ou l'admin", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseSuccessUsers.class)) }),
	    @ApiResponse(responseCode = "401", description = "identifiants incorrects", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }),
	    @ApiResponse(responseCode = "403", description = "l'admin doit changer son mot de passe", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }) })
    @Operation(summary = "Se Connecter")
    public ResponseEntity<Object> connexion(@RequestBody @Valid User_credentials user)
	    throws MessagingException, IOException {

	User userBD = userRepository.findByEmail(user.getEmail());

	int ValidationUser;

	ValidationUser = Uservice.connexion(user.getEmail(), user.getPassword(), userBD);

	if (ValidationUser == 0) {

	    AnotherResponse reponse_unauthorized = new AnotherResponse("401", "Vos identifiants sont incorrects");

	    return new ResponseEntity<>(reponse_unauthorized, HttpStatus.UNAUTHORIZED);

	} else if (ValidationUser == 1) {

	    ResponseSuccessUsers reponse_success = new ResponseSuccessUsers("200",
		    "Connexion reussie pour le consultant " + userBD.getNom(), userBD);

	    return new ResponseEntity<>(reponse_success, HttpStatus.OK);

	} else if (ValidationUser == 6) {

	    if (userBD.isPremiere_connexion()) {

		AnotherResponse response_forbidden = new AnotherResponse("403",
			"Connexion reussie pour l'admin ---doit impearativement changer son modt de passe");

		return new ResponseEntity<>(response_forbidden, HttpStatus.FORBIDDEN);

	    } else {

		ResponseSuccessUsers reponse_success = new ResponseSuccessUsers("200",
			"Connexion reussie pour l'admin " + userBD.getNom(), userBD);

		return new ResponseEntity<>(reponse_success, HttpStatus.OK);

	    }
	}

	else {

	    AnotherResponse reponse_unauthorized = new AnotherResponse("401", "Vos identifiants sont incorrects");

	    return new ResponseEntity<>(reponse_unauthorized, HttpStatus.UNAUTHORIZED);

	}

    }

    @GetMapping("/deconnexion")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deconnexion reussie", content = {
	    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }) })
    @Operation(summary = "Modifier la date d'expiration du token d'un utilisateur et renvoyer mail inscription")
    public ResponseEntity<Object> deconnexion(HttpServletRequest request, HttpServletResponse response)
	    throws MessagingException {

	HttpSession session = request.getSession();

	// probleme sur la deconnexion

	System.out.println(session.isNew());

	session.invalidate();

	System.out.println(session.getId());

	AnotherResponse response_success = new AnotherResponse("200", "Deconnexion reussie");

	return new ResponseEntity<>(response_success, HttpStatus.OK);

    }

    @GetMapping("/consultants")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "l'email a ete change avec succes", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }), })
    @Operation(summary = "liste de tous les marchands")
    public ResponseEntity<Object> get_marchands() {

	Iterable<User> listUsers = Uservice.getUsers();

	ResponseSuccessList reponse_correct = new ResponseSuccessList("200", "liste de tous les consultants",
		listUsers);

	return new ResponseEntity<>(reponse_correct, HttpStatus.OK);

    }

    @Operation(summary = "Creer un administarteur")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "utilisateur cree avec succes", content = {
	    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }),
	    @ApiResponse(responseCode = "409", description = "l'email existe deja dans la base de donnees", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = AnotherResponse.class)) }), })
    @PostMapping("/admin")
    public ResponseEntity<Object> emailAdmin(@RequestBody @Valid Email email) throws MessagingException {

	User admin_saved = Uservice.saveAdmin(email.getEmail(), Uservice.generer_mot_de_passe(25));

	if (!Objects.isNull(admin_saved)) {

	    AnotherResponse reponse_correct = new AnotherResponse("201",
		    "Un administrateur vient d'etre cree--un mail a ete envoye pour poursuivre son inscription");

	    return new ResponseEntity<>(reponse_correct, HttpStatus.CREATED);
	} else {

	    AnotherResponse response_conflict = new AnotherResponse("409",
		    "l'email existe deja dans la Base de donnees");

	    return new ResponseEntity<>(response_conflict, HttpStatus.CONFLICT);
	}

    }

}
