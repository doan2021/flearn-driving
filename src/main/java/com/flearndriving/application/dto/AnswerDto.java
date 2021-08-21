package com.flearndriving.application.dto;

public class AnswerDto {
    private Long answerId;
    private String content;

    public AnswerDto(Long answerId, String content) {
        super();
        this.answerId = answerId;
        this.content = content;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
