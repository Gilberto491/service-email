package com.serviceorder.email.controllers;

import com.serviceorder.email.Exception.EmailExceptionResponse;
import com.serviceorder.email.dtos.EmailDtoRequest;
import com.serviceorder.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService service;

    @PostMapping("/sending-email")
    public EmailExceptionResponse sendingEmail(@RequestBody @Validated EmailDtoRequest emailDtoRequest) {
        return service.sendEmail(emailDtoRequest);
    }
}
