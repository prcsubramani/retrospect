package com.sys.scrum.retro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.sys.scrum.retro.dto.CreateRetrospectRequest;
import com.sys.scrum.retro.dto.CreateScrumTeamMemberRequest;
import com.sys.scrum.retro.model.Retrospect;
import com.sys.scrum.retro.model.ScrumTeamMember;

@Mapper(componentModel = "spring")
public interface RetrospectMapper {	
    @Mapping(target = "scrumTeamMembers", ignore = true)
    @Mapping(target = "feedbackItems", ignore = true)
    @Mapping(target = "retrospectiveDate",dateFormat="yyyy-MM-dd")    
    Retrospect toRetrospect(CreateRetrospectRequest createRetrospect);
    
    @AfterMapping
    default void afterMapping(@MappingTarget Retrospect retrospect, CreateRetrospectRequest createRetrospect) {
      List<CreateScrumTeamMemberRequest> members = createRetrospect.getCreateScrumTeamMembers();
      List<ScrumTeamMember> participants = new ArrayList<>();
      ScrumTeamMember participant;
      for (int i = 0; i < members.size(); i++) {
    	  participant=new ScrumTeamMember();
    	  participant.setRetrospect(retrospect);
    	  participant.setTeamMemberCode(members.get(i).getScrumTeamMemberCode());
    	  participants.add(participant);
      }
      retrospect.setScrumTeamMembers(participants);
    }
}
               