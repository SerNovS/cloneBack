package com.tesis.ubb.tesis.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.ubb.tesis.models.Email.EmailValues;
import com.tesis.ubb.tesis.security.dto.Mensaje;
import com.tesis.ubb.tesis.service.EmailService;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = { "http://localhost:4200" })
public class EmailController {
    

    @Value("${spring.mail.username}")
    private String mailFrom;
    @Autowired
    EmailService emailService;

    
    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValues emailValues){
        emailValues.setMailFrom(mailFrom);
        emailValues.setSubject("Cambio de contraseña");
        emailValues.setUserName("Juan");
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        emailValues.setTokenPassword(tokenPassword);
        emailService.SendEmailTemplate(emailValues);
        return new ResponseEntity(new Mensaje("Te hemos enviado un correo."), HttpStatus.OK);
    }
}
