package com.vandung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vandung.entity.Demand;
import com.vandung.model.CandidateModel;
import com.vandung.service.CandidateService;
import com.vandung.service.DemandService;
import com.vandung.service.ExamListService;
import com.vandung.service.ExaminationService;

@Controller
public class CandidateController {

	@Autowired
	private ExaminationService examinationService;
	
	@Autowired
	private CandidateService candidateService;

	@Autowired
	private DemandService demandService;

	@Autowired
	private ExamListService examListService;

	@RequestMapping(value = "/dangky", method = RequestMethod.POST)
	public String addCandidate(@ModelAttribute CandidateModel candidateModel, Model model) {
		String object = candidateModel.object;
		String formal = candidateModel.formal;
		String level = candidateModel.level;
		Demand db = demandService.findDemand(object, formal, level);
		candidateModel.setDemand(db);
		candidateService.add(candidateModel);
		model.addAttribute("examination", examinationService.getExaminationNow());
		model.addAttribute("candidateModel", new CandidateModel());
		return "index";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ResponseEntity<Object> confirm(@RequestParam String arrCandidateConfirm) {
		String[] idCandidate = arrCandidateConfirm.trim().split(" ");
		for (int i = 0; i < idCandidate.length; i++) {
			examListService.updateCandidateConfirm(Integer.parseInt(idCandidate[i]));
		}
		return new ResponseEntity<Object>("success", HttpStatus.OK);
	}
}
