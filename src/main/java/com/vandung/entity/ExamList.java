package com.vandung.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exam_list")
public class ExamList implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_exam_list", nullable = false)
	private Integer idExamList;
	
	@Column(name = "total_cost")
	private Double totalCost;
	
	@Column(name = "confirm")
	private Boolean confirm;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_candidate")
	private Candidate candidate;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_examination")
	private Examination examination;
	
	public ExamList() {
		
	}

	public ExamList(Integer idExamList, Double totalCost, Boolean confirm, Candidate candidate, Examination examination) {
		super();
		this.idExamList = idExamList;
		this.totalCost = totalCost;
		this.confirm = confirm;
		this.candidate = candidate;
		this.examination = examination;
	}

	public Integer getIdExamList() {
		return idExamList;
	}

	public void setIdExamList(Integer idExamList) {
		this.idExamList = idExamList;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}
}
