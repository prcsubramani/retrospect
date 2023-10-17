package com.sys.scrum.retro.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

//This class represents scrum retrospect participants, generally employees participates in scrum retrospective ,so it is      named as ScrumTeamMember

@Data
@ToString(exclude = {"retrospect"})
@EqualsAndHashCode(exclude ={"retrospect"})
@Entity
@Table(name="scrum_team_member_info")
@IdClass(ScrumTeamMemberPk.class)
public class ScrumTeamMember{
	
	@Id
	@Column(name="team_member_code",length=10,nullable=false)
	private String teamMemberCode; 
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "retro_name")
	private Retrospect retrospect;

	public String getTeamMemberCode() {
		return teamMemberCode;
	}

	public void setTeamMemberCode(String teamMemberCode) {
		this.teamMemberCode = teamMemberCode;
	}

	public Retrospect getRetrospect() {
		return retrospect;
	}

	public void setRetrospect(Retrospect retrospect) {
		this.retrospect = retrospect;
	}
	
	

}
