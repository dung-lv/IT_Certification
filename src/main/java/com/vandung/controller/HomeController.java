package com.vandung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vandung.model.CandidateModel;
import com.vandung.service.ExaminationService;

@Controller
public class HomeController {
	
	public static Boolean statusLogin = false;
	@Autowired
	private ExaminationService examinationService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String addCandidate(Model model) {
		model.addAttribute("examination", examinationService.getExaminationNow());
		model.addAttribute("candidateModel", new CandidateModel());
		return "index";
	}
}
