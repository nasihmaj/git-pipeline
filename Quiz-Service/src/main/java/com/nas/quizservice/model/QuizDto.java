package com.nas.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
    String cat;
    Integer numQuestions;
    String quizTitle;
}
