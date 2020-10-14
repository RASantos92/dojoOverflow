package com.questions.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.questions.models.Answer;
import com.questions.models.Question;
import com.questions.service.AnswerService;
import com.questions.service.QuestionService;
import com.questions.service.TagService;

@Controller
public class HomeController {
	private static QuestionService questionServ;
	private static TagService tagServ;
	private static AnswerService answerServ;

	public HomeController(QuestionService questionServ, TagService tagServ, AnswerService answerServ) {
		this.questionServ = questionServ;
		this.tagServ = tagServ;
		this.answerServ = answerServ;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("questions", questionServ.getQuestions());
		model.addAttribute("tags", tagServ.getTags());
		return "questionDashboard.jsp";
	}

	@GetMapping("/new/question")
	public String creatQuestion(Model model) {
		model.addAttribute("newQuestionPlus", new Question());
		return "addQuestion.jsp";
	}

	@PostMapping("/question/new")
	public String createQuestionWithTags(@Valid @ModelAttribute("newQuestionPlus") Question newQuestionPlus,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addQuestion.jsp";
		} else {
			questionServ.CreateQuestionWithTags(newQuestionPlus);
			return "redirect:/";
		}
	}

	@GetMapping("/question/show/{id}")
	public String showQuestion(@PathVariable("id") Long id, Model model) {
		model.addAttribute("question", questionServ.getQuestion(id));
		model.addAttribute("newAnswer", new Answer());
		return "showQuestion.jsp";
	}

	@PostMapping("/answer/new")
	public String create(@Valid @ModelAttribute("newAnswer") Answer newAnswer, BindingResult result, Model model,
			@RequestParam("questionId") Long questionId) {
		if (result.hasFieldErrors()) {
			model.addAttribute("question", questionServ.getQuestion(questionId));
			return "redirect:/question/show/" + questionId;
		} else {
			answerServ.create(newAnswer);
			return "redirect:/";
		}
	}
}
