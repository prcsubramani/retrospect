package com.sys.scrum.retro.dto;


import java.util.List;

public class CreateRetrospectRequest{
	
	String retroName;
	String summary;
	String retrospectiveDate;
	List<CreateScrumTeamMemberRequest> createScrumTeamMembers;
	
	
	public String getRetroName() {
		return retroName;
	}
	public void setRetroName(String retroName) {
		this.retroName = retroName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getRetrospectiveDate() {
		return retrospectiveDate;
	}
	public void setRetrospectiveDate(String retrospectiveDate) {
		this.retrospectiveDate = retrospectiveDate;
	}
	
	public List<CreateScrumTeamMemberRequest> getCreateScrumTeamMembers() {
		return createScrumTeamMembers;
	}
	public void setCreateScrumTeamMembers(List<CreateScrumTeamMemberRequest> createScrumTeamMembers) {
		this.createScrumTeamMembers = createScrumTeamMembers;
	}     
	

}
