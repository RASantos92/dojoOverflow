package com.questions.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.questions.models.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
