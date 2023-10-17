package com.sys.scrum.retro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sys.scrum.retro.model.Retrospect;
import java.util.Date;


@Repository
public interface RetrospectRepository extends JpaRepository<Retrospect,String>{
	 Page<Retrospect> findByRetrospectiveDate(Date retrospectiveDate,Pageable pageable);
}
