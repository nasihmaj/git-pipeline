package com.nas.questionservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nas.questionservice.entity.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{
	
	
	
    @Query(value = "SELECT q.id FROM qz.questions q WHERE q.cat = :cat ORDER BY RAND() LIMIT :numQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCat(String cat, int numQuestions);


    List<Question> findByCat(String cat);

}
