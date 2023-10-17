package com.sys.scrum.retro.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.sys.scrum.retro.dto.CreateRetroFeedbackRequest;
import com.sys.scrum.retro.model.FeedbackType;
import com.sys.scrum.retro.model.Retrospect;
import com.sys.scrum.retro.model.RetrospectFeedback;

@Mapper(componentModel = "spring")
public interface RetrospectFeedbackMapper {

	@Mapping(target = "retroFeedback", ignore = true)
	@Mapping(target ="feedbackType", ignore =true)
	@Mapping(target ="id", ignore =true)
    RetrospectFeedback toRetrospect(CreateRetroFeedbackRequest createRetroFeedback);
	 @AfterMapping
	    default void afterMapping(@MappingTarget RetrospectFeedback retrospectFeedback, CreateRetroFeedbackRequest createRetroFeedback) {		 
		 FeedbackType feedbackType = FeedbackType.findByCode(createRetroFeedback.getFeedbackType());
		 retrospectFeedback.setFeedbackType(feedbackType);
		 Retrospect retrospect = new Retrospect();
		 retrospect.setRetroName(createRetroFeedback.getRetroName());
		 retrospectFeedback.setRetroFeedback(retrospect);
		}
}
