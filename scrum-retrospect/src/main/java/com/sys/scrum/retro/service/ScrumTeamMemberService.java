package com.sys.scrum.retro.service;



import com.sys.scrum.retro.model.ScrumTeamMember;

public interface ScrumTeamMemberService {
	
	ScrumTeamMember findByRetrospectAndParticipant(String retroName,String scrumTeamMember);

}
