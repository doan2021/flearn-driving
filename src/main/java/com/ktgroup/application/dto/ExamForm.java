package com.ktgroup.application.dto;

import java.util.List;

public class ExamForm {

    private String name;
    private List<String> listQuestionId;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getListQuestionId() {
        return listQuestionId;
    }
    public void setListQuestionId(List<String> listQuestionId) {
        this.listQuestionId = listQuestionId;
    }
    
    
}
