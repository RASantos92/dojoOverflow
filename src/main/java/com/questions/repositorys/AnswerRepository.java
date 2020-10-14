package com.questions.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.questions.models.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

}
