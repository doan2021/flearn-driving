package com.flearndriving.application.dto;

import java.util.List;

public class TrialExamDto {

    private Long examQuestionsId;
    private String nameExamQuestions;
    private int examMinutes;
    List<QuestionDto> listQuestions;

    public TrialExamDto(Long examQuestionsId, String nameExamQuestions, int examMinutes) {
        super();
        this.examQuestionsId = examQuestionsId;
        this.nameExamQuestions = nameExamQuestions;
        this.examMinutes = examMinutes;
    }

    public TrialExamDto(Long examQuestionsId, int examMinutes, List<QuestionDto> listQuestions) {
        super();
        this.examQuestionsId = examQuestionsId;
        this.examMinutes = examMinutes;
        this.listQuestions = listQuestions;
    }

    public Long getExamQuestionsId() {
        return examQuestionsId;
    }

    public void setExamQuestionsId(Long examQuestionsId) {
        this.examQuestionsId = examQuestionsId;
    }

    public int getExamMinutes() {
        return examMinutes;
    }

    public void setExamMinutes(int examMinutes) {
        this.examMinutes = examMinutes;
    }

    public List<QuestionDto> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<QuestionDto> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public String getNameExamQuestions() {
        return nameExamQuestions;
    }

    public void setNameExamQuestions(String nameExamQuestions) {
        this.nameExamQuestions = nameExamQuestions;
    }

}
