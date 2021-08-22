package com.flearndriving.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.common.Constant;
import com.flearndriving.application.dto.LearningProgress;
import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.Chapter;
import com.flearndriving.application.exception.BusinessException;
import com.flearndriving.application.responsitories.ChapterResponsitory;
import com.flearndriving.application.responsitories.QuestionsRespository;
import com.flearndriving.application.responsitories.StatusLearnRespository;

@Service
public class ChapterServices {
    
    @Autowired
    ChapterResponsitory chapterResponsitory;
    
    @Autowired
    AccountServices accountsServices;
    
    @Autowired
    QuestionsRespository questionsRespository;
    
    @Autowired
    StatusLearnRespository statusLearnRespository;
    
    public Chapter getChapterDetail(Long chapterId) {
        Chapter chapter = chapterResponsitory.findByChapterId(chapterId);
        if (chapter == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Chương không tồn tại!");
        }
        return chapter;
    }
    
    public List<Chapter> findAllChapter() {
        return chapterResponsitory.findAll();
    }
    
    public Integer countChapter() {
        return chapterResponsitory.countChapter();
    }
    
    public List<LearningProgress> learningProgressChapter() {
        List<LearningProgress> listLearningProgressChapter = new ArrayList<>();
        List<Chapter> listChapter = chapterResponsitory.findAll();
        for (Chapter chapter : listChapter) {
            Account account = accountsServices.getAccountLogin();
            LearningProgress learningProgress = new LearningProgress();
            learningProgress.setChapter(chapter);
            learningProgress.setProgressChapter(getNumberLearnProgress(chapter));
            learningProgress.setKnowledge(statusLearnRespository.countQuestionWithStatus(chapter, account, 3));
            learningProgress.setFamilier(statusLearnRespository.countQuestionWithStatus(chapter, account, 2));
            learningProgress.setRest(statusLearnRespository.countQuestionRest(chapter, account));
            listLearningProgressChapter.add(learningProgress);
        }
        return listLearningProgressChapter;
    }
    
    public Double getNumberLearnProgress(Chapter chapter) {
        Account account = accountsServices.getAccountLogin();
        int knowledge = statusLearnRespository.countQuestionWithStatus(chapter, account, 3);
        return ((knowledge * 1.0) / chapter.getListQuestion().size()) * 100;
    }

    public List<LearningProgress> countLearnedChapter() {
        List<LearningProgress> listLearningProgressChapter = new ArrayList<>();
        List<Chapter> listChapter = chapterResponsitory.findAll();
        for (Chapter lChapter : listChapter) {
            LearningProgress learningProgress = new LearningProgress();
            learningProgress.setProgressChapter(getNumberLearnProgress(lChapter));
            if (learningProgress.getProgressChapter() == 100) {
                listLearningProgressChapter.add(learningProgress);
            }
        }
        return listLearningProgressChapter;
    }
}
