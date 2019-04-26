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
@Table(name = "certificate")
public class Certificate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_certificate", nullable = false)
	private Integer idCertificate;
	
	@Column(name = "diploma_number")
	private Integer diplomaNumber;
	
	@Column(name = "notebook_number")
	private Integer notebookNumber;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_candidate")
	private Candidate candidate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_examination")
	private Examination examination;
	
	public Certificate() {
		
	}

	public Certificate(Integer idCertificate, Integer diplomaNumber, Integer notebookNumber, 
			Candidate candidate, Examination examination) {
		super();
		this.idCertificate = idCertificate;
		this.diplomaNumber = diplomaNumber;
		this.notebookNumber = notebookNumber;
		this.candidate = candidate;
		this.examination = examination;
	}

	public Integer getIdCertificate() {
		return idCertificate;
	}

	public void setIdCertificate(Integer idCertificate) {
		this.idCertificate = idCertificate;
	}

	public Integer getDiplomaNumber() {
		return diplomaNumber;
	}

	public void setDiplomaNumber(Integer diplomaNumber) {
		this.diplomaNumber = diplomaNumber;
	}

	public Integer getNotebookNumber() {
		return notebookNumber;
	}

	public void setNotebookNumber(Integer notebookNumber) {
		this.notebookNumber = notebookNumber;
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
