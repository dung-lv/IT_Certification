package com.vandung.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vandung.entity.Member;
import com.vandung.service.CertificateService;
import com.vandung.service.DemandService;
import com.vandung.service.ExamListService;
import com.vandung.service.ExaminationService;
import com.vandung.service.MemberService;
import com.vandung.service.RankService;
import com.vandung.service.ResultService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private RankService rankService;
	
	@Autowired
	private ExaminationService examinationService;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private ExamListService examlistService;
	
	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	private DemandService demandService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String defaultPage() {
		return "login";
	}
	
	@RequestMapping(value = "/dotthi", method = RequestMethod.GET)
	public String examinationPage(ModelMap model, @RequestParam(value = "pagination", required=false, defaultValue = "0") String pagination) {
		if("0".equals(pagination)) {
			pagination = "1";
		}
		List<Member> memberList = new ArrayList<Member>();
		memberList = memberService.getPagination(Integer.parseInt(pagination));
		long count = memberService.getCount();
		float paginationSize = count/10;
		if(count % 10 != 0) {
			paginationSize += 1;
		}
		model.addAttribute("memberList", memberList);
		//model.addAttribute("memberList", memberService.getPagination(Integer.parseInt(pagination)));
		model.addAttribute("rankList", rankService.getAll());
		model.addAttribute("examination", examinationService.getExaminationNow());
		model.addAttribute("paginationSize", (int)paginationSize);
		if("0".equals(pagination)) {
			return "login";
		}else {
			HomeController.statusLogin = true;
			return "dot_thi_thanh_vien";
		}
	}
	
	@GetMapping("/hoidong")
	public String councilPage(Model model) {
		model.addAttribute("examination", examinationService.getExaminationNow());
		if(HomeController.statusLogin) {
			return "danh_sach_hoi_dong_thi";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/thi")
	public String examlistPage(Model model) {
		model.addAttribute("examination", examinationService.getExaminationNow());
		model.addAttribute("examlist", examlistService.getListByConfirm());
		if(HomeController.statusLogin) {
			return "danh_sach_thi";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/thutien")
	public String tuitionlistPage(Model model) {
		model.addAttribute("examination", examinationService.getExaminationNow());
		model.addAttribute("examlist", examlistService.getListByNotConfirm());
		if(HomeController.statusLogin) {
			return "danh_sach_thu_tien";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/thubai")
	public String lessonlístPage(Model model) {
		model.addAttribute("examination", examinationService.getExaminationNow());
		model.addAttribute("resultlist", examlistService.getListByConfirm());
		if(HomeController.statusLogin) {
			return "danh_sach_thu_bai";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/ketquathi")
	public String examresultPage(Model model) {
		model.addAttribute("examination", examinationService.getExaminationNow());
		model.addAttribute("resultlist", resultService.getAllByExamination());
		if(HomeController.statusLogin) {
			return "ket_qua_thi";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/chungchi")
	public String certificatelistPage(Model model) {
		model.addAttribute("examination", examinationService.getExaminationNow());
		model.addAttribute("certificateList", certificateService.getAllByExamination());
		if(HomeController.statusLogin) {
			return "danh_sach_chung_chi";
		}else {
			return "login";
		}
	}
	
	@GetMapping("/dulieu")
	public String dataSystemPage(Model model) {
		model.addAttribute("demandList", demandService.getAll());
		if(HomeController.statusLogin) {
			return "du_lieu_he_thong";
		}else {
			return "login";
		}
	}
}
