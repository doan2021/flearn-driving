package com.doanfpt.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.application.common.Constant;
import com.doanfpt.application.entities.Chapter;
import com.doanfpt.application.responsitories.ChapterResponsitory;

@Service
public class ChapterServices {
    
    @Autowired
    ChapterResponsitory chapterResponsitory;
    
    public Chapter getOne(Long chapterId) {
        return chapterResponsitory.getOne(chapterId);
    }
    
    public List<Chapter> getAllChapter() {
        return chapterResponsitory.findByIsDeleteOrderByName(Constant.IS_NOT_DELETE);
    }
    
    public Integer countChapter() {
        return chapterResponsitory.countChapter();
    }
}
