package com.vandung.model;

import com.vandung.entity.Candidate;
import com.vandung.entity.Demand;

public class CandidateModel {

	public Integer idCandidate;
	
	public String nameCandidate;
	
	public String email;
	
	public String dateOfBirth;
	
	public String sex;
	
	public String code;
	
	public String phone;
	
	public String registrationNumber;
	
	public String note;
	 
	public String object;
	
	public String formal;
	
	public String level;
	
	public Demand demand;
	
	public CandidateModel() {
		
	}

	public CandidateModel(Candidate db) {
		super();
		this.idCandidate = db.getIdCandidate();
		this.nameCandidate = db.getNameCandidate();
		this.email = db.getEmail();
		this.dateOfBirth = db.getDateOfBirth().toString();
		if(db.getSex()) {
			this.sex = "1";
		}
		else {
			this.sex = "0";
		}
		this.code = db.getCode();
		this.phone = db.getPhone();
		this.registrationNumber = db.getRegistrationNumber();
		this.note = db.getNote();
		this.demand = db.getDemand();
	}

	public Integer getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(Integer idCandidate) {
		this.idCandidate = idCandidate;
	}

	public String getNameCandidate() {
		return nameCandidate;
	}

	public void setNameCandidate(String nameCandidate) {
		this.nameCandidate = nameCandidate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getFormal() {
		return formal;
	}

	public void setFormal(String formal) {
		this.formal = formal;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}
}
