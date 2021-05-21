package com.ktgroup.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktgroup.application.entities.Chapter;
import com.ktgroup.application.responsitories.ChapterResponsitory;

@Service
public class ChapterServices {
    
    @Autowired
    ChapterResponsitory chapterResponsitory;
    
    public Chapter getOne(Long chapterId) {
        return chapterResponsitory.getOne(chapterId);
    }
    
    public void saveChapter(Chapter chapter) {
        chapterResponsitory.save(chapter);
    }
    
    public List<Chapter> getAllChapter() {
        List<Chapter> listChapter = new ArrayList<>();
        listChapter = chapterResponsitory.findAll();
        if (listChapter != null) {
            return listChapter;
        }
        return new ArrayList<Chapter>();
    }
}
