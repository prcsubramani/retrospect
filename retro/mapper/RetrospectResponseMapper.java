package com.sys.scrum.retro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.sys.scrum.retro.dto.RetrospectFeedbackResponse;
import com.sys.scrum.retro.dto.RetrospectResponse;
import com.sys.scrum.retro.dto.ScrumTeamMemberResponse;
import com.sys.scrum.retro.model.FeedbackType;
import com.sys.scrum.retro.model.Retrospect;
import com.sys.scrum.retro.model.RetrospectFeedback;
import com.sys.scrum.retro.model.ScrumTeamMember;

@Mapper(componentModel = "spring")
public interface RetrospectResponseMapper {
	
	 @Mapping(target="scrumTeamMemberRespList",ignore=true)
	 @Mapping(target="feedbackResponseList",ignore=true)
	 @Mapping(target = "retrospectiveDate",dateFormat="yyyy-MM-dd") 
	 RetrospectResponse toRetrospectResponse(Retrospect retrospect);    
	    @AfterMapping
	    default void afterResponseMapping(@MappingTarget RetrospectResponse retrospectResponse, Retrospect retrospect) {
	    	
	      List<ScrumTeamMember> members =retrospect.getScrumTeamMembers(); 
	      List<ScrumTeamMemberResponse> memberListResponse = new ArrayList<>();
	      ScrumTeamMemberResponse scrumTeamMemberResponse;
	      for (int i = 0; i < members.size(); i++) {
	    	  scrumTeamMemberResponse=new ScrumTeamMemberResponse();
	    	  scrumTeamMemberResponse.setScrumTeamMember(members.get(i).getTeamMemberCode());
	    	  memberListResponse.add(scrumTeamMemberResponse);
	       }
	      retrospectResponse.setScrumTeamMemberRespList(memberListResponse);
	      
	      List<RetrospectFeedback> feedbackItems = retrospect.getFeedbackItems();
	      List<RetrospectFeedbackResponse> feedbackListResponse = new ArrayList<>();
	      RetrospectFeedbackResponse retrospectFeedbackResponse;
	      for (int i = 0; i < feedbackItems.size(); i++) {
	    	  retrospectFeedbackResponse=new RetrospectFeedbackResponse();
	    	  RetrospectFeedback retrospectFeedback = feedbackItems.get(i);
	    	  retrospectFeedbackResponse.setTeamMemberCode(retrospectFeedback.getTeamMemberCode());
	    	  retrospectFeedbackResponse.setFeedbackId(retrospectFeedback.getId());
	    	  if(retrospectFeedback.getFeedbackType()!=null) {
	    	    retrospectFeedbackResponse.setFeedbackType(retrospectFeedback.getFeedbackType().getCode());
	    	  }else {
	    		retrospectFeedbackResponse.setFeedbackType(FeedbackType.IDEA.getCode()); 
	    	  }
	    	  retrospectFeedbackResponse.setFeedBack(feedbackItems.get(i).getCommentsBody());
	    	  feedbackListResponse.add(retrospectFeedbackResponse);
	       }
	      retrospectResponse.setFeedbackResponseList(feedbackListResponse);
	    }
	    
	    List<RetrospectResponse> toRetrospectResponseList(List<Retrospect> retrospectList);

}
