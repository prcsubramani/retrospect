package com.sys.scrum.retro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sys.scrum.retro.model.ScrumTeamMember;
import com.sys.scrum.retro.model.ScrumTeamMemberPk;



public interface ScrumTeamMemberRepository extends JpaRepository<ScrumTeamMember, ScrumTeamMemberPk>{
	
	@Query("SELECT t FROM ScrumTeamMember t WHERE t.retrospect.retroName = ?1 and t.teamMemberCode = ?2 ")
	ScrumTeamMember findByRetrospectAndParticipant(String retroName,String scrumTeamMember);
}
