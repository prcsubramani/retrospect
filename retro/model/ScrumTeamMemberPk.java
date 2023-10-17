package com.sys.scrum.retro.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrumTeamMemberPk implements java.io.Serializable {

	private static final long serialVersionUID = -852606248225598322L;	

	private String teamMemberCode;
	
	private String retrospect;

	@Override
	public int hashCode() {
		return Objects.hash(retrospect, teamMemberCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScrumTeamMemberPk other = (ScrumTeamMemberPk) obj;
		return Objects.equals(retrospect, other.retrospect) && Objects.equals(teamMemberCode, other.teamMemberCode);
	}
	
	
	
	
}
