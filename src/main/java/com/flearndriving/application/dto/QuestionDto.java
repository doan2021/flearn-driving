package com.flearndriving.application.dto;

import java.util.List;

public class QuestionDto {
    private Long questionId;
    private int number;
    private String content;
    private List<AnswerDto> listAnswers;
    private List<String> listUrlImage;

    public QuestionDto(Long questionId, int number, String content) {
        super();
        this.questionId = questionId;
        this.number = number;
        this.content = content;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AnswerDto> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<AnswerDto> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getListUrlImage() {
        return listUrlImage;
    }

    public void setListUrlImage(List<String> listUrlImage) {
        this.listUrlImage = listUrlImage;
    }

}
