package com.ktgroup.application.dto;

import java.util.List;

public class ExamForm {

    private String name;
    private List<Long> listQuestionId;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Long> getListQuestionId() {
        return listQuestionId;
    }
    public void setListQuestionId(List<Long> listQuestionId) {
        this.listQuestionId = listQuestionId;
    }
    
    
}
