package com.vandung.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_candidate")
	private Integer idCandidate;
	
	@Column(name = "name_candidate")
	private String nameCandidate;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "dob")
	private Date dateOfBirth;
	
	@Column(name = "sex")
	private Boolean sex;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "registration_number")
	private String registrationNumber;
	
	@Column(name = "note")
	private String note;
	 
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_demand")
	private Demand demand;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_examination")
	private Examination examination;
	
	public Candidate() {
		
	}

	public Candidate(Integer idCandidate, String nameCandidate, String email, Date dateOfBirth, Boolean sex,
			String code, String phone, String registrationNumber, String note, Demand demand, Examination examination) {
		super();
		this.idCandidate = idCandidate;
		this.nameCandidate = nameCandidate;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.code = code;
		this.phone = phone;
		this.registrationNumber = registrationNumber;
		this.note = note;
		this.demand = demand;
		this.examination = examination;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
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

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}
}
