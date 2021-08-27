package com.serviceorder.email.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Data
public class EmailDtoRequest {

    private String ownerRef;
    @Email
    private String emailFrom;
    @Email
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;

}
