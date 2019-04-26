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
import javax.persistence.Table;

@Entity
@Table(name = "ranks")
public class Ranks implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rank", nullable = false)
	private Integer idRank;
	
	@Column(name = "name_rank")
	private String nameRank;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_mission")
	private Mission mission;
	
	public Ranks() {
		
	}

	public Ranks(Integer idRank, String nameRank, Mission mission) {
		super();
		this.idRank = idRank;
		this.nameRank = nameRank;
		this.mission = mission;
	}

	public Integer getIdRank() {
		return idRank;
	}

	public void setIdRank(Integer idRank) {
		this.idRank = idRank;
	}

	public String getNameRank() {
		return nameRank;
	}

	public void setNameRank(String nameRank) {
		this.nameRank = nameRank;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}
}
