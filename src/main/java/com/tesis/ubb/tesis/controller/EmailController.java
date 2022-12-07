package com.tesis.ubb.tesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.ubb.tesis.models.Email.EmailValues;
import com.tesis.ubb.tesis.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
    
    @Autowired
    EmailService emailService;

    
    @PostMapping("/send-html")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValues emailValues){
        emailService.SendEmailTemplate(emailValues);
        return new ResponseEntity("Correo enviado con éxito", HttpStatus.OK);
    }
}
