package com.sys.scrum.retro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.scrum.retro.model.RetrospectFeedback;

import com.sys.scrum.retro.repository.RetroFeedbackRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RetroFeedbackServiceImpl implements RetroFeedbackService{       
	
    @Autowired
	RetroFeedbackRepository  retroFeedbackRepository;
	
	public RetrospectFeedback  createRetroFeedback(RetrospectFeedback comment){
		return retroFeedbackRepository.save(comment);
	}
	
	
	public RetrospectFeedback  getRetroFeedbackByIdAndRetrospect(Long id,String retro) {
		return retroFeedbackRepository.findByIdInAndRetroFeedback(id,retro);
	}
	
	

}
