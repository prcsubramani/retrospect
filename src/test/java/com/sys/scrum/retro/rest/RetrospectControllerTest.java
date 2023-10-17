package com.sys.scrum.retro.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sys.scrum.retro.dto.CreateRetroFeedbackRequest;
import com.sys.scrum.retro.dto.CreateRetrospectRequest;
import com.sys.scrum.retro.dto.CreateScrumTeamMemberRequest;
import com.sys.scrum.retro.dto.RetrospectFeedbackResponse;
import com.sys.scrum.retro.dto.RetrospectResponse;
import com.sys.scrum.retro.model.Retrospect;
import com.sys.scrum.retro.model.ScrumTeamMember;
import com.sys.scrum.retro.repository.RetroFeedbackRepository;
import com.sys.scrum.retro.repository.RetrospectRepository;


@SpringBootTest(
		webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = "spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true"
)
public class RetrospectControllerTest {
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Autowired
	RetrospectRepository retrospectRepository;
	
	@Autowired
	RetroFeedbackRepository  retroFeedbackRepository;  
	
	@BeforeEach
    void setUp() {
		retrospectRepository.deleteAll();
		retroFeedbackRepository.deleteAll();
		
    }
	
	@Test
	void testCreateRetroSpect(){
		CreateRetrospectRequest retrospectRequest =getDefaultCreateRetrospectRequest();		
		ResponseEntity<RetrospectResponse> responseEntity=testRestTemplate.postForEntity(API_RETROSPECT_URL, retrospectRequest, RetrospectResponse.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().getRetroName()).isEqualTo(retrospectRequest.getRetroName());  
		assertThat(responseEntity.getBody().getSummary()).isEqualTo(retrospectRequest.getSummary());
		assertThat(responseEntity.getBody().getRetrospectiveDate()).isNotEqualTo(retrospectRequest.getRetrospectiveDate());
		assertThat(responseEntity.getBody().getScrumTeamMemberRespList().size()).isEqualTo(retrospectRequest.getCreateScrumTeamMembers().size());
		
	}
	

	@Test
	void testAddRetrospectComment(){		
		Retrospect retrospect=this.retrospectRepository.save(getDefaultRetrospect());
		CreateRetroFeedbackRequest request=getDefaultCreateFeedbackRequest();
		String url=String.format(API_RETROSPECT_ADD_FEEDBACK_URL,retrospect.getRetroName());		
		ResponseEntity<RetrospectFeedbackResponse> retrospectFeedbackResponse=testRestTemplate.postForEntity(url, getDefaultCreateFeedbackRequest(), RetrospectFeedbackResponse.class);
		assertThat(retrospectFeedbackResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(retrospectFeedbackResponse.getBody().getFeedBack()).isEqualTo(request.getCommentsBody());  
		assertThat(retrospectFeedbackResponse.getBody().getTeamMemberCode()).isEqualTo(request.getTeamMemberCode());
		assertThat(retrospectFeedbackResponse.getBody().getFeedbackType()).isNotEqualTo(request.getFeedbackType());
		
	}
	
	CreateRetrospectRequest  getDefaultCreateRetrospectRequest(){		
		CreateRetrospectRequest  createRetrospectRequest = new CreateRetrospectRequest();
		createRetrospectRequest.setRetroName("RETRO20");
		createRetrospectRequest.setRetrospectiveDate("2023-12-01");
		createRetrospectRequest.setSummary("Retrospect");
		CreateScrumTeamMemberRequest  participant1=new CreateScrumTeamMemberRequest();
		participant1.setScrumTeamMemberCode("Clare");
		CreateScrumTeamMemberRequest  participant2=new CreateScrumTeamMemberRequest();
		participant2.setScrumTeamMemberCode("Alex");
		CreateScrumTeamMemberRequest  participant3=new CreateScrumTeamMemberRequest();
		participant3.setScrumTeamMemberCode("Derek");
		List<CreateScrumTeamMemberRequest> participantList = new ArrayList<>();
		participantList.add(participant1);
		participantList.add(participant2);
		participantList.add(participant3);
		createRetrospectRequest.setCreateScrumTeamMembers(participantList);
		return createRetrospectRequest;
	}
	
	CreateRetroFeedbackRequest  getDefaultCreateFeedbackRequest(){		
		CreateRetroFeedbackRequest  createRetroFeedbackRequest = new CreateRetroFeedbackRequest();
		createRetroFeedbackRequest.setFeedbackType("Positive");		
		createRetroFeedbackRequest.setCommentsBody("Everything went well in this sprint");
		createRetroFeedbackRequest.setTeamMemberCode("DEREK");
		return createRetroFeedbackRequest;
		
	} 
		
	 Retrospect  getDefaultRetrospect(){		
		Retrospect  retrospect = new Retrospect();
		retrospect.setRetroName("RETRO01");
		try 
		{
            retrospect.setRetrospectiveDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse("2023-12-01" ) );
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }		
		retrospect.setSummary("Sample summary about scrum retrospective");
		ScrumTeamMember  participant1=new ScrumTeamMember();
		participant1.setTeamMemberCode("Clare");
		ScrumTeamMember  participant2=new ScrumTeamMember();
		participant2.setTeamMemberCode("Clare");
		ScrumTeamMember  participant3=new ScrumTeamMember();
		participant3.setTeamMemberCode("Clare");
		List<ScrumTeamMember> participantList = new ArrayList<>();
		participantList.add(participant1);
		participantList.add(participant2);
		participantList.add(participant3);
		retrospect.setScrumTeamMembers(participantList);
		return retrospect;
	}
		
	
	private static final String  API_RETROSPECT_URL="/retrospect";
	private static final String  API_RETROSPECT_ADD_FEEDBACK_URL="/retrospect/%s/addcomment";
	
	

}
