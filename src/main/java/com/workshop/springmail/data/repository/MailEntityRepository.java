package com.workshop.springmail.data.repository;

import com.workshop.springmail.data.model.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailEntityRepository extends JpaRepository<MailEntity, Long> {
}
