package com.doanfpt.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.doanfpt.application.entities.Chapter;
import com.doanfpt.application.entities.Chapter_;
import com.doanfpt.application.responsitories.ChapterResponsitory;

@Service
public class ChapterServices {
    
    @Autowired
    ChapterResponsitory chapterResponsitory;
    
    public Chapter getOne(Long chapterId) {
        return chapterResponsitory.getOne(chapterId);
    }
    
    public List<Chapter> findAllChapter() {
        return chapterResponsitory.findAll(
                Sort.by(Sort.Direction.ASC, Chapter_.INDEX).and(Sort.by(Sort.Direction.ASC, Chapter_.UPDATE_AT)));
    }
    
    public Integer countChapter() {
        return chapterResponsitory.countChapter();
    }
}
