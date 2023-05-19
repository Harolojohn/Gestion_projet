package com.ApiGP.Service;

import java.util.Objects;
import java.util.Random;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiGP.Models.User;
import com.ApiGP.Models.User_Infos;
import com.ApiGP.Repository.UserRepository;

@Service
@Transactional
public class Userservice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public User save(User user) {

	return userRepository.save(user);

    }

    public User Create_consultant(User_Infos user) {

	User savedUser = new User();
	User findUser = new User();

	User user_infos = new User();

	user_infos.setNom(user.getNom());
	user_infos.setEmail(user.getEmail());
	user_infos.setPassword(user.getPassword());

	findUser = userRepository.findByEmail(user.getEmail());

	if (Objects.isNull(findUser)) {

	    // user.setPassword(get_SHA_512_SecurePassword(user.getPassword(),"bonjour"));

	    savedUser = userRepository.save(user_infos);

	    return savedUser;

	} else {

	    return user_infos;
	}
    }

    public int sendCode(String email) throws MessagingException {

	User userSaved = userRepository.findByEmail(email);

	if (Objects.isNull(userSaved)) {

	    return 0;

	} else {

	    int code_envoye = emailService.send_Code(userSaved);

	    return code_envoye;
	}
    }

    public User AlterPassword(String email, String newpassword) throws MessagingException {

	User user_a_modifie = userRepository.findByEmail(email);

	if (Objects.isNull(user_a_modifie)) {

	    return user_a_modifie;

	} else {

	    user_a_modifie.setPassword(newpassword);

	    return userRepository.save(user_a_modifie);
	}

    }

    public User finduserbyEmail(String email) {

	return userRepository.findByEmail(email);

    }

    public Iterable<User> findAdministarteurs() {

	return userRepository.findByrole("administrateur");

    }

    public Iterable<User> getUsers() {

	return userRepository.findAll();

    }

    public int connexion(String email, String password, User userBD) {

	if (Objects.isNull(userBD)) {

	    return 0;

	} else {

	    if (userBD.getPassword().equals(password)) {

		if (userBD.getRole().equals("consultant")) {

		    return 1;

		} else {

		    return 6;

		}
	    } else {

		return 0;
	    }
	}

    }

    public String generer_mot_de_passe(int len) {

	String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String Small_chars = "abcdefghijklmnopqrstuvwxyz";
	String numbers = "0123456789";
	String symbols = "!@#$%^&*_=+-/.?<>(%*{}:~)";

	String values = Capital_chars + Small_chars + numbers + symbols;

	Random rndm_method = new Random();

	char[] password = new char[len];

	for (int i = 0; i < len; i++) {

	    int numero = rndm_method.nextInt(88);

	    password[i] = values.charAt(numero);

	}
	return password.toString();

    }

    public User saveAdmin(String email_admin, String password) throws MessagingException {

	User savedUser;
	User findUser;

	findUser = userRepository.findByEmail(email_admin);

	if (Objects.isNull(findUser)) {

	    User user = new User();

	    user.setEmail(email_admin);
	    user.setNom("");

	    user.setPassword(password);

	    user.setRole("administrateur");

	    savedUser = userRepository.save(user);

	    emailService.EmailInscriptionAdmin(savedUser);

	    return savedUser;

	} else {

	    return null;
	}

    }

}
