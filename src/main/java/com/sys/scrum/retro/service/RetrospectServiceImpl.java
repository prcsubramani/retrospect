package com.sys.scrum.retro.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sys.scrum.retro.model.Retrospect;
import com.sys.scrum.retro.repository.RetrospectRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RetrospectServiceImpl implements RetrospectService {
	
    @Autowired
	RetrospectRepository repository;	
	
	
    @Override
    public Retrospect  getRetrospectById(String retroName) {
        return repository.findById(retroName).get();
    }
	
    @Override
	public Retrospect createRetrospect(Retrospect retrospect) {
    	return this.repository.save(retrospect);
	}
	
    @Override
	public Retrospect updateRetrospect(Retrospect retrospect) {
    	return this.repository.save(retrospect);
	}
	
	public Page<Retrospect> getAllRetrospects(Pageable paging) {
      		return this.repository.findAll(paging);
	}
	
	public Page<Retrospect> getRetrospectByDate(Date date,Pageable paging) {
		return this.repository.findByRetrospectiveDate(date,paging);
	}

}
