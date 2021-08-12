package com.serviceorder.email.services.impl;

import com.serviceorder.email.Exception.EmailExceptionResponse;
import com.serviceorder.email.dtos.EmailDtoRequest;
import com.serviceorder.email.repositories.EmailRepository;
import com.serviceorder.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.util.StringUtils.hasText;

public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailRepository repository;

    @Override
    public EmailExceptionResponse sendEmail(EmailDtoRequest dto) {
        if(validateField(dto)) {
            return EmailExceptionResponse
                    .builder()
                    .messageError("Campos vazios")
                    .success(false)
                    .build();
        }
        return null;
    }

    private boolean validateField(EmailDtoRequest dto) {

        return hasText(dto.getEmailFrom()) &&
                hasText(dto.getEmailTo()) &&
                hasText(dto.getOwnerRef()) &&
                hasText(dto.getSubject()) &&
                hasText(dto.getText());
    }
}
