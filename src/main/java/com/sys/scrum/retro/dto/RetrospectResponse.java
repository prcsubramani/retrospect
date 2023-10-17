package com.sys.scrum.retro.dto;


import java.util.Date;
import java.util.List;

public class RetrospectResponse {
	
	
	String retroName;

	
	String summary;

	
	Date retrospectiveDate;

	
	private List<ScrumTeamMemberResponse> scrumTeamMemberRespList;

	
	private List<RetrospectFeedbackResponse> feedbackResponseList;


	public List<RetrospectFeedbackResponse> getFeedbackResponseList() {
		return feedbackResponseList;
	}


	public void setFeedbackResponseList(List<RetrospectFeedbackResponse> feedbackResponseList) {
		this.feedbackResponseList = feedbackResponseList;
	}


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


	public Date getRetrospectiveDate() {
		return retrospectiveDate;
	}


	public void setRetrospectiveDate(Date retrospectiveDate) {
		this.retrospectiveDate = retrospectiveDate;
	}


	public List<ScrumTeamMemberResponse> getScrumTeamMemberRespList() {
		return scrumTeamMemberRespList;
	}


	public void setScrumTeamMemberRespList(List<ScrumTeamMemberResponse> scrumTeamMemberRespList) {
		this.scrumTeamMemberRespList = scrumTeamMemberRespList;
	}  
	
}
