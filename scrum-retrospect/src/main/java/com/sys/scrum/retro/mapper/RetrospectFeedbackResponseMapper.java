package com.sys.scrum.retro.mapper;



import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.sys.scrum.retro.dto.RetrospectFeedbackResponse;
import com.sys.scrum.retro.model.RetrospectFeedback;


@Mapper(componentModel = "spring")
public interface RetrospectFeedbackResponseMapper {

	
	@Mapping(target ="feedbackType", ignore =true)
	@Mapping(target ="feedbackId", source="id")
	@Mapping(target ="feedBack", source="commentsBody")
    RetrospectFeedbackResponse toRetrospectFeedbackResponse(RetrospectFeedback retrospectFeedback);
	 @AfterMapping
	    default void afterResponseMapping(@MappingTarget RetrospectFeedbackResponse retrospectFeedbackResponse, RetrospectFeedback retrospectFeedback   ) {
		  retrospectFeedbackResponse.setFeedbackType(retrospectFeedback.getFeedbackType().getCode()); 
	    }            
	 
}
