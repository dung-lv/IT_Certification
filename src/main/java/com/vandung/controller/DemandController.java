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
import com.vandung.entity.Demand;
import com.vandung.service.DemandService;

@RestController
public class DemandController {

	@Autowired
	private DemandService demandService;

	@RequestMapping(value = "/create-demand", method = RequestMethod.POST)
	public ResponseEntity<Object> createOrUpdateDemand(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		Demand db = new Demand();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			db.setObject(jsonNode.get("object").asText());
			db.setLevel(jsonNode.get("level").asText());
			db.setFormal(jsonNode.get("formal").asText());
			db.setReviewCost(jsonNode.get("reviewCost").asInt());
			db.setExamCost(jsonNode.get("examCost").asInt());
			Integer id = jsonNode.get("idDemand").asInt();
			if (id != 0) {
				db.setIdDemand(id);
				demandService.update(db);
			} else {
				demandService.add(db);
			}
			return new ResponseEntity<Object>("success", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new Demand(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/delete-demand", method = RequestMethod.POST)
	public void deleteDemand(@RequestParam String id) {
		demandService.delete(Integer.parseInt(id));
	}

}
