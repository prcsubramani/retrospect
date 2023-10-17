           package com.sys.scrum.retro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sys.scrum.retro.model.RetrospectFeedback;
import com.sys.scrum.retro.model.RetrospectFeedbackPk;

@Repository
public interface RetroFeedbackRepository extends JpaRepository<RetrospectFeedback,RetrospectFeedbackPk> {
     
	 @Query("SELECT t FROM RetrospectFeedback t WHERE t.id = ?1 AND t.retroFeedback.retroName = ?2")
	 RetrospectFeedback findByIdInAndRetroFeedback(Long id, String retroName);
	
}
