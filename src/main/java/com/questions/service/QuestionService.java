package com.questions.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.questions.models.Question;
import com.questions.repositorys.QuestionRepository;

@Service
public class QuestionService {
	private static QuestionRepository questionRepo;

	public QuestionService(QuestionRepository questionRepo) {
		this.questionRepo = questionRepo;
	}

	public Question create(Question newQuestion) {
		return questionRepo.save(newQuestion);
	}

	public List<Question> getQuestion() {
		return (List<Question>) questionRepo.findAll();
	}

	public Question getQuestion(Long id) {
		Optional<Question> question = questionRepo.findById(id);
		return question.isPresent() ? question.get() : null;
	}

	public Question saveQuestion(Question question) {
		return questionRepo.save(question);
	}

}