package com.vandung.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mission")
public class Mission implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mission", nullable = false)
	private Integer idMission;
	
	@Column(name = "name_mission")
	private String nameMission;
	
	public Mission() {
		
	}

	public Mission(Integer idMission, String nameMission) {
		super();
		this.idMission = idMission;
		this.nameMission = nameMission;
	}

	public Integer getIdMission() {
		return idMission;
	}

	public void setIdMission(Integer idMission) {
		this.idMission = idMission;
	}

	public String getNameMission() {
		return nameMission;
	}

	public void setNameMission(String nameMission) {
		this.nameMission = nameMission;
	}
}
