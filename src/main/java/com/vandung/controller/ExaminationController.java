package com.vandung.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandung.entity.Examination;
import com.vandung.entity.Member;
import com.vandung.service.ExaminationService;
import com.vandung.service.MemberService;

@RestController
public class ExaminationController {

	@Autowired
	private ExaminationService examinationService;

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/create-examination", method = RequestMethod.POST)
	public ResponseEntity<Object> createExamination(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		Examination entity = new Examination();

		try {
			jsonNode = objectMapper.readTree(dataJson);

			String pattern = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date = sdf.parse(jsonNode.get("dateExam").asText());
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			
			entity.setDateExam(sqlStartDate);
			entity.setCode(jsonNode.get("code").asText());
			entity.setContent(jsonNode.get("content").asText());
			Iterator<JsonNode> arrayIterator = jsonNode.get("members").iterator();
			List<Integer> idMembers = new ArrayList<Integer>();
			while(arrayIterator.hasNext()) {
			    JsonNode elementInX = arrayIterator.next();
			    Integer id = elementInX.asInt();
			    idMembers.add(id);
			}
			List<Member> lstMember = new ArrayList<Member>();
			for (int i = 0; i < idMembers.size(); i++) {
				Member member = memberService.getMemberById((idMembers.get(i)));
				member.setIsUse(true);
				memberService.update(member);
				lstMember.add(member);
			}
			entity.setMembers(lstMember);
			examinationService.add(entity);
			return new ResponseEntity<Object>("success", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new Examination(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/update-examination", method = RequestMethod.POST)
	public ResponseEntity<Object> updateExamination(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		Examination db = new Examination();

		try {
			jsonNode = objectMapper.readTree(dataJson);
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date = sdf.parse(jsonNode.get("dateExam").asText());
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
	        
			db.setIdExamination(jsonNode.get("idExamination").asInt());
			db.setDateExam(sqlStartDate);
			db.setCode(jsonNode.get("code").asText());
			Iterator<JsonNode> arrayIterator = jsonNode.get("members").iterator();
			List<Integer> idMembers = new ArrayList<Integer>();
			while(arrayIterator.hasNext()) {
			    JsonNode elementInX = arrayIterator.next();
			    Integer id = elementInX.asInt();
			    idMembers.add(id);
			}
			List<Member> lstMember = new ArrayList<Member>();
			for (int i = 0; i < idMembers.size(); i++) {
				Member member = memberService.getMemberById((idMembers.get(i)));
				member.setIsUse(true);
				memberService.update(member);
				lstMember.add(member);
			}
			if(lstMember != null) {				
				db.setMembers(lstMember);
			}
			examinationService.update(db);
			return new ResponseEntity<Object>("success", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new Examination(), HttpStatus.BAD_REQUEST);
		}
	}
}
