package com.vandung.entity;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.Table;

@Entity
@Table(name = "examination")
public class Examination implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_examination", nullable = false)
	private Integer idExamination;
	
	@Column(name = "date_exam")
	private Date dateExam;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "code")
	private String code;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "council", 
	joinColumns = {@JoinColumn(name = "id_examination", referencedColumnName = "id_examination")}, 
	inverseJoinColumns = {@JoinColumn(name = "id_member", referencedColumnName = "id_member")})
	private List<Member> members;
	
	public Examination() {
		
	}

	public Examination(Integer idExamination, Date dateExam, String content, String code, List<Member> members) {
		super();
		this.idExamination = idExamination;
		this.dateExam = dateExam;
		this.content = content;
		this.code = code;
		this.members = members;
	}

	public Integer getIdExamination() {
		return idExamination;
	}

	public void setIdExamination(Integer idExamination) {
		this.idExamination = idExamination;
	}

	public Date getDateExam() {
		return dateExam;
	}

	public void setDateExam(Date dateExam) {
		this.dateExam = dateExam;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
}
