package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Document;

@Repository
public interface DocumentRespository  extends JpaRepository<Document, Long> {

}
