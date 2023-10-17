package com.sys.scrum.retro.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sys.scrum.retro.exception.NoValidFeedbackTypeException;
import com.sys.scrum.retro.exception.NotValidParticipantException;
import com.sys.scrum.retro.exception.ParticipantNotFoundException;
import com.sys.scrum.retro.exception.RetrospectDateNotFoundException;

@ControllerAdvice
public class RetroResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler { 
	
	@ExceptionHandler(value = RetrospectDateNotFoundException.class)
	public ResponseEntity<Object> exception(RetrospectDateNotFoundException exception) {
	   return new ResponseEntity<>("Retrospective date not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ParticipantNotFoundException.class)
	public ResponseEntity<Object> exception(ParticipantNotFoundException exception) {
	   return new ResponseEntity<>("Scrum team member not found", HttpStatus.NOT_FOUND);
	}  
	
	@ExceptionHandler(value = NoValidFeedbackTypeException.class)
	public ResponseEntity<Object> exception(NoValidFeedbackTypeException exception) {
	   return new ResponseEntity<>("Not a valid Feedback type ", HttpStatus.NOT_FOUND);
	}      
	
	@ExceptionHandler(value = NotValidParticipantException.class)
	public ResponseEntity<Object> exception(NotValidParticipantException exception) {
	   return new ResponseEntity<>("Not a valid participant ", HttpStatus.NOT_FOUND);
	}     

}
