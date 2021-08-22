package com.flearndriving.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.entities.Chapter;

@Repository
public interface ChapterResponsitory extends JpaRepository<Chapter, Long> {

    @Query("SELECT count(c) FROM Chapter c WHERE c.isDelete = false")
    Integer countChapter();
    
    @Query("SELECT c FROM Chapter c WHERE c.isDelete = false ORDER BY c.index")
    List<Chapter> findAll();

    @Query("SELECT c FROM Chapter c WHERE c.isDelete = false AND c.chapterId = :chapterId")
    Chapter findByChapterId(Long chapterId);
}
