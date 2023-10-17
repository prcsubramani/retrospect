package com.sys.scrum.retro.dto;

import java.util.Objects;

public class CreateRetroFeedbackRequest {
	
	String retroName;
	String teamMemberCode;	
	String commentsBody; 
	String feedbackType;
	public String getRetroName() {
		return retroName;
	}
	public void setRetroName(String retroName) {
		this.retroName = retroName;
	}
	
	public String getTeamMemberCode() {
		return teamMemberCode;
	}
	public void setTeamMemberCode(String teamMemberCode) {
		this.teamMemberCode = teamMemberCode;
	}
	public String getCommentsBody() {
		return commentsBody;
	}
	public void setCommentsBody(String commentsBody) {
		this.commentsBody = commentsBody;
	}
	public String getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(commentsBody, feedbackType, teamMemberCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateRetroFeedbackRequest other = (CreateRetroFeedbackRequest) obj;
		return Objects.equals(commentsBody, other.commentsBody) && Objects.equals(feedbackType, other.feedbackType)
				&& Objects.equals(teamMemberCode, other.teamMemberCode);
	}

	

}
