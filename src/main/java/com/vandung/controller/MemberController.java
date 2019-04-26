package com.vandung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandung.entity.Member;
import com.vandung.entity.Ranks;
import com.vandung.service.MemberService;
import com.vandung.service.RankService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private RankService rankService;

	@RequestMapping(value = "/create-member", method = RequestMethod.POST)
	public ResponseEntity<Object> createOrUpdateMember(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		Member db = new Member();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			db.setNameMember(jsonNode.get("nameMember").asText());
			db.setWorkUnit(jsonNode.get("workUnit").asText());
			db.setNote(jsonNode.get("note").asText());
			Ranks ranksDb = rankService.getRankById(jsonNode.get("rank").asInt());
			db.setRank(ranksDb);
			db.setIsUse(false);
			Integer id = jsonNode.get("idMember").asInt();
			if (id != 0) {
				db.setIdMember(id);
				memberService.update(db);
			} else {
				memberService.add(db);
			}
			return new ResponseEntity<Object>("success", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new Member(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/delete-member", method = RequestMethod.POST)
	public void deleteMember(@RequestParam String id) {
		memberService.delete(Integer.parseInt(id));
	}

}
