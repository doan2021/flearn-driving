package com.flearndriving.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.entities.Chapter;

@Repository
public interface ChapterResponsitory extends JpaRepository<Chapter, Long> {

    @Query("SELECT count(c) FROM Chapter c")
    Integer countChapter();

	public Chapter findByChapterId(Long chapterId);
}
