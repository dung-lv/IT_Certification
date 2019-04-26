package com.vandung.entity;

import java.io.Serializable;

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
@Table(name = "result")
public class Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_result", nullable = false)
	private Integer idResult;
	
	@Column(name = "sight_scores")
	private Double sightScores;
	
	@Column(name = "drill_scores")
	private Double drillScores;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_candidate")
	private Candidate candidate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_examination")
	private Examination examination;
	
	public Result() {
		
	}

	public Result(Integer idResult, Double sightScores, Double drillScores, Candidate candidate, Examination examination) {
		super();
		this.idResult = idResult;
		this.sightScores = sightScores;
		this.drillScores = drillScores;
		this.candidate = candidate;
		this.examination = examination;
	}

	public Integer getIdResult() {
		return idResult;
	}

	public void setIdResult(Integer idResult) {
		this.idResult = idResult;
	}

	public Double getSightScores() {
		return sightScores;
	}

	public void setSightScores(Double sightScores) {
		this.sightScores = sightScores;
	}
	
	public Double getDrillScores() {
		return drillScores;
	}

	public void setDrillScores(Double drillScores) {
		this.drillScores = drillScores;
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
