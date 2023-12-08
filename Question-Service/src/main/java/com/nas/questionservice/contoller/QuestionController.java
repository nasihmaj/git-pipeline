package com.nas.questionservice.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nas.questionservice.entity.Question;
import com.nas.questionservice.entity.QuestionWrapper;
import com.nas.questionservice.entity.Response;
import com.nas.questionservice.service.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion(){
		return questionService.findAll();
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question){
		return questionService.addQuestion(question);
	}
	
	@GetMapping("/cat/{cat}")
	public List<Question> findQuestionsByCategory(@PathVariable String cat){
		return questionService.getQuestionsByCat(cat);
	}

	@GetMapping("/question/{id}")
	public Optional<Question> getbyId(@PathVariable Integer id) {
		return questionService.getQuestionsById(id);
	}
	
	@GetMapping("gen")
	  public ResponseEntity<List<Integer>> getQuestionsForQuiz
      (@RequestParam String cat, @RequestParam Integer numQuestions ){
  return questionService.getQuestionsForQuiz(cat, numQuestions);
}
	
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionId){
		return questionService.getQuestionsFromId(questionId);
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
	}
	
	
}
