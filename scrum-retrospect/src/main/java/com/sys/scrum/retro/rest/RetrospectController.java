package com.sys.scrum.retro.rest;



import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sys.scrum.retro.dto.CreateRetroFeedbackRequest;
import com.sys.scrum.retro.dto.CreateRetrospectRequest;
import com.sys.scrum.retro.dto.RetrospectFeedbackResponse;
import com.sys.scrum.retro.dto.RetrospectResponse;
import com.sys.scrum.retro.exception.NoValidFeedbackTypeException;
import com.sys.scrum.retro.exception.NotValidParticipantException;
import com.sys.scrum.retro.exception.ParticipantNotFoundException;
import com.sys.scrum.retro.exception.RetrospectDateNotFoundException;
import com.sys.scrum.retro.mapper.RetrospectFeedbackMapper;
import com.sys.scrum.retro.mapper.RetrospectFeedbackResponseMapper;
import com.sys.scrum.retro.mapper.RetrospectMapper;
import com.sys.scrum.retro.mapper.RetrospectResponseMapper;
import com.sys.scrum.retro.model.FeedbackType;
import com.sys.scrum.retro.model.Retrospect;
import com.sys.scrum.retro.model.RetrospectFeedback;
import com.sys.scrum.retro.model.ScrumTeamMember;
import com.sys.scrum.retro.service.RetroFeedbackService;
import com.sys.scrum.retro.service.RetrospectService;
import com.sys.scrum.retro.service.ScrumTeamMemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/retrospect")
public class RetrospectController {
	private static final Logger logger = LoggerFactory.getLogger(RetrospectController.class);
	
	@Autowired
	RetrospectService retroService;
	
	@Autowired
	RetroFeedbackService  retroFeedbackService;
	
	@Autowired
	ScrumTeamMemberService  scrumTeamMemberService;
	
    private final RetrospectMapper retrospectMapper;
    private final RetrospectFeedbackMapper retrospectFeedbackMapper;	
    private final RetrospectResponseMapper retrospectResponseMapper;
    private final RetrospectFeedbackResponseMapper retrospectFeedbackResponseMapper;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public RetrospectResponse createRetrospect(@RequestBody CreateRetrospectRequest createRetrospectReequest) {	
		logger.debug("Entering createRetrospect >>");
		if(createRetrospectReequest.getRetrospectiveDate()==null) {
			throw new RetrospectDateNotFoundException();
		}
		if(createRetrospectReequest.getCreateScrumTeamMembers()!=null && createRetrospectReequest.getCreateScrumTeamMembers().isEmpty()) {
			throw new ParticipantNotFoundException();
		}
		Retrospect retrospect=retrospectMapper.toRetrospect(createRetrospectReequest);
		retrospect=retroService.createRetrospect(retrospect);
		logger.debug("Scrum retrospect created with name >> "+retrospect.getRetroName());
		logger.debug("Exiting createRetrospect <<");
		return retrospectResponseMapper.toRetrospectResponse(retrospect);
	}	
	
	@PostMapping(path="/{retrospectId}/addcomment",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public RetrospectFeedbackResponse addComment(@PathVariable String retrospectId, @Valid   @RequestBody CreateRetroFeedbackRequest createRetroFeedbackReequest) {
		logger.debug("Entering createComment >>");
		if(createRetroFeedbackReequest.getFeedbackType()==null || FeedbackType.findByCode(createRetroFeedbackReequest.getFeedbackType())==null) {
			throw new NoValidFeedbackTypeException();
		}
		if(createRetroFeedbackReequest.getTeamMemberCode()==null) {
			throw new NotValidParticipantException();
		}
		ScrumTeamMember scrumTeamMember = scrumTeamMemberService.findByRetrospectAndParticipant(retrospectId, createRetroFeedbackReequest.getTeamMemberCode());
		if(scrumTeamMember==null) {
			throw new NotValidParticipantException();
		}		
		RetrospectFeedback feedback = retrospectFeedbackMapper.toRetrospect(createRetroFeedbackReequest);
		Retrospect retrospect =retroService.getRetrospectById(retrospectId);
		feedback.setRetroFeedback(retrospect);	
		logger.debug("Feedback Type >> "+feedback.getFeedbackType().getCode());
		logger.debug("Exiting createComment <<");
		return retrospectFeedbackResponseMapper.toRetrospectFeedbackResponse(retroFeedbackService.createRetroFeedback(feedback));	
	}
	
	@GetMapping(path="/retrospects",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<RetrospectResponse> getAllRetrospect(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="3") int size){
		logger.debug("Entering getAllRetrospect >>");
		Pageable paging = PageRequest.of(page, size);		
		Page<Retrospect> pagingList =retroService.getAllRetrospects(paging);
		logger.debug("Exiting getAllRetrospect       <<");
		return retrospectResponseMapper.toRetrospectResponseList(pagingList.getContent());
	}
	
	@GetMapping(path="/all/{retrospectiveDate}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<RetrospectResponse> getRetrospectByDate(@PathVariable  @DateTimeFormat(pattern ="dd-MM-yyyy") Date retrospectiveDate ,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="3") int size){
		logger.debug("Entering getRetrospectByDate >>");
		Pageable paging = PageRequest.of(page, size);		
		Page<Retrospect> pagingList =retroService.getRetrospectByDate(retrospectiveDate,paging);
		logger.debug("Exiting  getRetrospectByDate >>");       
		return retrospectResponseMapper.toRetrospectResponseList(pagingList.getContent());
	}	
	@PutMapping(path="/{retrospectId}/comments/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public RetrospectFeedbackResponse updateFeedback(@PathVariable String retrospectId, @PathVariable Long id, @Valid   @RequestBody CreateRetroFeedbackRequest createRetroFeedback) {
		logger.debug("Entering updateFeedback >>");
		RetrospectFeedback feedback = retrospectFeedbackMapper.toRetrospect(createRetroFeedback);
		RetrospectFeedback  existingFeedback=retroFeedbackService.getRetroFeedbackByIdAndRetrospect(id,retrospectId);
		existingFeedback.setCommentsBody(feedback.getCommentsBody());
		existingFeedback.setFeedbackType(feedback.getFeedbackType());
		logger.debug("Exiting  updateFeedback <<");
		return retrospectFeedbackResponseMapper.toRetrospectFeedbackResponse(existingFeedback); 
	}
	
	

}
