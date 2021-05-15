package com.ktgroup.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Images;

@Repository
public interface ImagesRespository  extends JpaRepository<Images, Long> {

}
