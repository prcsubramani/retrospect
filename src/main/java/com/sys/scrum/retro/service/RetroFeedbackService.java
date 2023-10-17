package com.sys.scrum.retro.service;

import com.sys.scrum.retro.model.RetrospectFeedback;

public interface RetroFeedbackService {	
	RetrospectFeedback createRetroFeedback(RetrospectFeedback comments);	
	RetrospectFeedback  getRetroFeedbackByIdAndRetrospect(Long id,String retro);
	
}
