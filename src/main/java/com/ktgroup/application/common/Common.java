package com.ktgroup.application.common;

public class Common {

    public static float percentQuestion(int correctNumber, int incorrectNumber) {
        return (correctNumber * 100.0f) / (correctNumber + incorrectNumber);
    }
}
