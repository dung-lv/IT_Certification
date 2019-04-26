package com.vandung.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "demand")
public class Demand implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_demand", nullable = false)
	private Integer idDemand;
	
	@Column(name = "object")
	private String object;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "formal")
	private String formal;
	
	@Column(name = "review_cost")
	private Integer reviewCost;
	
	@Column(name = "exam_cost")
	private Integer examCost;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	public Demand() {
		
	}

	public Demand(Integer idDemand, String object, String level, String formal, Integer reviewCost,
			Integer examCost, Date updateDate) {
		super();
		this.idDemand = idDemand;
		this.object = object;
		this.level = level;
		this.formal = formal;
		this.reviewCost = reviewCost;
		this.examCost = examCost;
		this.updateDate = updateDate;
	}

	public Integer getIdDemand() {
		return idDemand;
	}

	public void setIdDemand(Integer idDemand) {
		this.idDemand = idDemand;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFormal() {
		return formal;
	}

	public void setFormal(String formal) {
		this.formal = formal;
	}

	public Integer getReviewCost() {
		return reviewCost;
	}

	public void setReviewCost(Integer reviewCost) {
		this.reviewCost = reviewCost;
	}

	public Integer getExamCost() {
		return examCost;
	}

	public void setExamCost(Integer examCost) {
		this.examCost = examCost;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
