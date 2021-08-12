package com.serviceorder.email.repositories;

import com.serviceorder.email.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {}
