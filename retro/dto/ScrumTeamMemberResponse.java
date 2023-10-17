package com.sys.scrum.retro.dto;

public class ScrumTeamMemberResponse {
	
	public ScrumTeamMemberResponse() {}
	
	public ScrumTeamMemberResponse(String scrumTeamMember) {
		this.scrumTeamMember = scrumTeamMember;
	}

	private String scrumTeamMember;

	public String getScrumTeamMember() {
		return scrumTeamMember;
	}

	public void setScrumTeamMember(String scrumTeamMember) {
		this.scrumTeamMember = scrumTeamMember;
	}

}
