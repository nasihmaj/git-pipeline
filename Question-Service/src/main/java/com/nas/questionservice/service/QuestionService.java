package com.nas.questionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nas.questionservice.entity.Question;
import com.nas.questionservice.entity.QuestionWrapper;
import com.nas.questionservice.entity.Response;
import com.nas.questionservice.repo.QuestionRepo;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepo questionRepo;
	
	
		public ResponseEntity<List<Question>> findAll() {
			
			 return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
		}
	
		public ResponseEntity<String> addQuestion(Question question) {
			questionRepo.save(question);
	        return new ResponseEntity<>("success",HttpStatus.CREATED);
		}
	
		public List<Question> getQuestionsByCat(String cat) {
		    return questionRepo.findByCat(cat);  
		}
	
		public Optional<Question> getQuestionsById(Integer id) {
			return questionRepo.findById(id);	
		}
		
		public ResponseEntity<List<Integer>> getQuestionsForQuiz( String cat, Integer numQuestions){
			List<Integer> questions = questionRepo.findRandomQuestionsByCat(cat, numQuestions);
			  return new ResponseEntity<>(questions, HttpStatus.OK);
		}
	
		public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionId) {
			List<QuestionWrapper> wrappers = new ArrayList<>();
			List<Question> questions = new ArrayList<>();
			
			for(Integer id: questionId) {
				Optional<Question> optionalQuestion = questionRepo.findById(id);
				if (optionalQuestion.isPresent()) {
					questions.add(optionalQuestion.get());
				}
			}
			
			for(Question question: questions) {
				QuestionWrapper wrapper = new QuestionWrapper();
				wrapper.setId(question.getId());
				wrapper.setQuestionTitle(question.getQuestionTitle());
				wrapper.setOption1(question.getOption1());
				wrapper.setOption2(question.getOption2());
				wrapper.setOption3(question.getOption3());
				
				wrappers.add(wrapper);
			}
			return new ResponseEntity<>(wrappers, HttpStatus.OK);
		}
	
		public ResponseEntity<Integer> getScore(List<Response> responses) {
			
			int right =0;
			for(Response response: responses) {
				Question question = questionRepo.findById(response.getId()).get();
				if(response.getResponse().equals(question.getRightAnswer()))
					right++;
			}
			
			return new ResponseEntity<>(right, HttpStatus.OK);
		}
		
}
