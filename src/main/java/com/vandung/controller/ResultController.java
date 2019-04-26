package com.vandung.controller;

import java.util.ArrayList;
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
import com.vandung.entity.Result;
import com.vandung.service.ResultService;

@RestController
public class ResultController {

	@Autowired
	private ResultService resultService;

	@RequestMapping(value = "/update-result", method = RequestMethod.POST)
	public ResponseEntity<Object> updateResult(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			Iterator<JsonNode> arrIdResult = jsonNode.get("arrIdResult").iterator();
			List<Integer> idResults = new ArrayList<Integer>();
			while(arrIdResult.hasNext()) {
			    JsonNode elementInX = arrIdResult.next();
			    Integer id = elementInX.asInt();
			    idResults.add(id);
			}
			Iterator<JsonNode> arrSightScores = jsonNode.get("arrSightScores").iterator();
			List<Double> sightScores = new ArrayList<Double>();
			while(arrSightScores.hasNext()) {
			    JsonNode elementInX = arrSightScores.next();
			    Double el = elementInX.asDouble();
			    sightScores.add(el);
			}
			Iterator<JsonNode> arrDrillScores = jsonNode.get("arrDrillScores").iterator();
			List<Double> drillScores = new ArrayList<Double>();
			while(arrDrillScores.hasNext()) {
			    JsonNode elementInX = arrDrillScores.next();
			    Double el = elementInX.asDouble();
			    drillScores.add(el);
			}
			for(int i=0; i<sightScores.size(); i++) {
				Result res = new Result();
				res.setIdResult(idResults.get(i));
				res.setSightScores(sightScores.get(i));
				res.setDrillScores(drillScores.get(i));
				resultService.update(res);
			}
			return new ResponseEntity<Object>("success", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new Result(), HttpStatus.BAD_REQUEST);
		}
	}

}
