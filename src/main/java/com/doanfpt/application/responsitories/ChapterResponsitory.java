package com.doanfpt.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Chapter;

@Repository
public interface ChapterResponsitory  extends JpaRepository<Chapter, Long> {
  
    public List<Chapter> findByIsDeleteOrderByName(Boolean isDelete);
  
    @Query ("SELECT count(c) FROM Chapter c WHERE c.isDelete = false")
    Integer countChapter();
}
