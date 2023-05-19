package com.ApiGP.Service;

import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ApiGP.Models.User;

@Service
public class EmailService {

    @Autowired
    private final Properties properties;

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(Properties properties, TemplateEngine templateEngine, JavaMailSender javaMailSender)
	    throws MessagingException {

	this.properties = properties;
	this.templateEngine = templateEngine;
	this.javaMailSender = javaMailSender;

    }

    public int send_Code(User user) throws MessagingException {

	Context context = new Context();

	Random code = new Random();

	int max = 999999;
	int min = 111111;
	int code_envoye;

	code_envoye = code.nextInt(max - min + 1) + min;

	context.setVariable("title", "Reset Your password");
	context.setVariable("code", "Votre code de validation pour changer votre mot de passe:" + code_envoye);

	// create an HTML template and pass the variables to it

	String body = templateEngine.process("resetPassword", context);

	// Send the verification email

	MimeMessage message = javaMailSender.createMimeMessage();

	MimeMessageHelper helper = new MimeMessageHelper(message, true);
	helper.setTo(user.getEmail());
	helper.setSubject("Reset Your password");
	helper.setText(body, true);

	javaMailSender.send(message);

	return code_envoye;

    }

    public void EmailInscriptionAdmin(User user) throws MessagingException {

	Context context = new Context();

	context.setVariable("title", "mail d'inscription pour un nouvel administrateur");
	context.setVariable("link", "");
	context.setVariable("password", user.getPassword());

	// create an HTML template and pass the variables to it

	String body = templateEngine.process("emailNewAdmin", context);

	// Send the verification email

	MimeMessage message = javaMailSender.createMimeMessage();

	MimeMessageHelper helper = new MimeMessageHelper(message, true);
	helper.setTo(user.getEmail());
	helper.setSubject("email inscription");
	helper.setText(body, true);

	javaMailSender.send(message);

    }

}
