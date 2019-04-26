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
import com.vandung.entity.Certificate;
import com.vandung.service.CertificateService;

@RestController
public class CertificateController {

	@Autowired
	private CertificateService certificateService;
	
	@RequestMapping(value = "/update-certificate", method = RequestMethod.POST)
	public ResponseEntity<Object> updateCertificate(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			Iterator<JsonNode> arrIdCertificate = jsonNode.get("arrIdCertificate").iterator();
			List<Integer> idCertificate = new ArrayList<Integer>();
			while(arrIdCertificate.hasNext()) {
			    JsonNode elementInX = arrIdCertificate.next();
			    Integer id = elementInX.asInt();
			    idCertificate.add(id);
			}
			Iterator<JsonNode> arrDiplomaNumber = jsonNode.get("arrDiplomaNumber").iterator();
			List<Integer> diplomaNumbers = new ArrayList<Integer>();
			while(arrDiplomaNumber.hasNext()) {
			    JsonNode elementInX = arrDiplomaNumber.next();
			    Integer el = elementInX.asInt();
			    diplomaNumbers.add(el);
			}
			Iterator<JsonNode> arrNotebookNumber = jsonNode.get("arrNotebookNumber").iterator();
			List<Integer> notebookNumbers = new ArrayList<Integer>();
			while(arrNotebookNumber.hasNext()) {
			    JsonNode elementInX = arrNotebookNumber.next();
			    Integer el = elementInX.asInt();
			    notebookNumbers.add(el);
			}
			for(int i=0; i<idCertificate.size(); i++) {
				Certificate cer = new Certificate();
				cer.setIdCertificate(idCertificate.get(i));
				cer.setDiplomaNumber(diplomaNumbers.get(i));
				cer.setNotebookNumber(notebookNumbers.get(i));
				certificateService.update(cer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>("success", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(new Certificate(), HttpStatus.OK);
	}
}
