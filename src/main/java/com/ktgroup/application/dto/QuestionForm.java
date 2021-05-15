package com.ktgroup.application.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class QuestionForm {
    private int number;
    private String content;
    private List<AnswerForm> listAnswers;
    private MultipartFile[] images;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AnswerForm> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<AnswerForm> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
