package com.serviceorder.email.services;

import com.serviceorder.email.Exception.EmailExceptionResponse;
import com.serviceorder.email.dtos.EmailDtoRequest;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    EmailExceptionResponse sendEmail(EmailDtoRequest dto);
}
