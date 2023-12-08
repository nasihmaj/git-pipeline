package com.nas.quizservice.service;

import com.nas.quizservice.QuizInterface;
import com.nas.quizservice.model.QuestionWrapper;
import com.nas.quizservice.model.Quiz;
import com.nas.quizservice.model.Response;
import com.nas.quizservice.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String cat, int numQuestions, String quizTitle) {
        List<Integer>  questionsIds = quizInterface.getQuestionsForQuiz(cat, numQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setQuizTitle(quizTitle);
        quiz.setQuestionIds(questionsIds);
        quizRepo.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }

}
