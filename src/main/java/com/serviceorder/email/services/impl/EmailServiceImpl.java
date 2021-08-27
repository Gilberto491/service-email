package com.serviceorder.email.services.impl;

import com.serviceorder.email.Exception.EmailExceptionResponse;
import com.serviceorder.email.dtos.EmailDtoRequest;
import com.serviceorder.email.entities.Email;
import com.serviceorder.email.repositories.EmailRepository;
import com.serviceorder.email.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;
    private final JavaMailSender emailSender;

    @Override
    public EmailExceptionResponse sendEmail(EmailDtoRequest dto) {
        if (validateField(dto)) {
            return EmailExceptionResponse
                    .builder()
                    .messageError("Campos vazios")
                    .success(false)
                    .build();
        }

        return saveEmail(dto);

    }

    private boolean validateField(EmailDtoRequest dto) {

        return isNull(dto.getEmailFrom()) &&
                isNull(dto.getEmailTo()) &&
                isNull(dto.getOwnerRef()) &&
                isNull(dto.getSubject()) &&
                isNull(dto.getText());
    }

    public EmailExceptionResponse saveEmail(EmailDtoRequest dto) {
        dto.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(dto.getEmailFrom());
            simpleMailMessage.setTo(dto.getEmailTo());
            simpleMailMessage.setSubject(dto.getSubject());
            simpleMailMessage.setText(dto.getText());
            emailSender.send(simpleMailMessage);

            Email email = new Email();
            BeanUtils.copyProperties(dto, email);

            repository.saveAndFlush(email);

            return EmailExceptionResponse
                    .builder()
                    .success(true)
                    .build();
        } catch (MailException e) {
            return EmailExceptionResponse
                    .builder()
                    .messageError(e.getMessage())
                    .success(false)
                    .build();
        }
    }
}
