package com.vandung.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_member", nullable = false)
	private Integer idMember;
	
	@Column(name = "name_member")
	private String nameMember;
	
	@Column(name = "work_unit")
	private String workUnit;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "is_use")
	private Boolean isUse;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rank")
	private Ranks rank;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "council", 
	joinColumns = {@JoinColumn(name = "id_member", referencedColumnName = "id_member")}, 
	inverseJoinColumns = {@JoinColumn(name = "id_examination", referencedColumnName = "id_examination")})
	private List<Examination> examinations;
	
	public Member() {
		
	}

	public Member(Integer idMember, String nameMember, String workUnit, String note, Boolean isUse, Ranks rank,
			List<Examination> examinations) {
		super();
		this.idMember = idMember;
		this.nameMember = nameMember;
		this.workUnit = workUnit;
		this.note = note;
		this.isUse = isUse;
		this.rank = rank;
		this.examinations = examinations;
	}

	public Integer getIdMember() {
		return idMember;
	}

	public void setIdMember(Integer idMember) {
		this.idMember = idMember;
	}

	public String getNameMember() {
		return nameMember;
	}

	public void setNameMember(String nameMember) {
		this.nameMember = nameMember;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public Boolean getIsUse() {
		return isUse;
	}

	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Ranks getRank() {
		return rank;
	}

	public void setRank(Ranks rank) {
		this.rank = rank;
	}

	public List<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}
}
