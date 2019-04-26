package com.vandung.model;

import com.vandung.entity.Ranks;

public class MemberModel {

	public Integer idMember;

	public String nameMember;

	public String workUnit;

	public String note;
	
	public Integer idRank;
	
	public String nameRank;
	
	public Ranks rank;
	
	public MemberModel() {
		
	}

	public MemberModel(Integer idMember, String nameMember, String workUnit, String note, Ranks rank) {
		super();
		this.idMember = idMember;
		this.nameMember = nameMember;
		this.workUnit = workUnit;
		this.note = note;
		this.rank = rank;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Ranks getRank() {
		return rank;
	}

	public void setRank(Ranks rank) {
		this.rank = rank;
	}
}
