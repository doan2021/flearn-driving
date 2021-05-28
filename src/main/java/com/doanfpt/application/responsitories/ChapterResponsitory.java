package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Chapter;

@Repository
public interface ChapterResponsitory  extends JpaRepository<Chapter, Long> {

}
