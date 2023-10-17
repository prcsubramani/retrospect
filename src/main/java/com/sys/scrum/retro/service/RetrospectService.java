package com.sys.scrum.retro.service;



import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sys.scrum.retro.model.Retrospect;

public interface RetrospectService {
	
	
	Retrospect getRetrospectById(String retroName);
	Retrospect createRetrospect(Retrospect retrospect);
	Retrospect updateRetrospect(Retrospect retrospect);
	Page<Retrospect> getAllRetrospects(Pageable paging);	
	Page<Retrospect> getRetrospectByDate(Date date,Pageable paging);

}
