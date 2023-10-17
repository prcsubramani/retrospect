package com.sys.scrum.retro.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sys.scrum.retro.model.ScrumTeamMember;
import com.sys.scrum.retro.repository.ScrumTeamMemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScrumTeamMemberServiceImpl implements ScrumTeamMemberService {
	
	@Autowired
	ScrumTeamMemberRepository scrumTeamMemberRepository;	
	
	public ScrumTeamMemberServiceImpl(ScrumTeamMemberRepository scrumTeamMemberRepository) {
	  this.scrumTeamMemberRepository=scrumTeamMemberRepository;
	}
	
	public ScrumTeamMember findByRetrospectAndParticipant(String retroName,String scrumTeamMember) {
		return this.scrumTeamMemberRepository.findByRetrospectAndParticipant(retroName, scrumTeamMember);	
	}
    


}
