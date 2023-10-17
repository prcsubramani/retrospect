package com.sys.scrum.retro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "retroFeedback")
@EqualsAndHashCode(exclude = "retroFeedback")
@Entity
@Table(name="retro_feedback_info")
@IdClass(RetrospectFeedbackPk.class)
public class RetrospectFeedback implements java.io.Serializable {  
	/**
	 * 
	 */
	private static final long serialVersionUID = 6897750308332813674L;	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Id
	@Column(name="team_member_code",length=10,nullable=false)
	private String teamMemberCode; 
	
	@Id	 
	@ManyToOne(fetch = FetchType.LAZY)	 
	@JoinColumn(name = "retro_name") 
	private Retrospect retroFeedback;
	 
		               
	@Column(name="feedback_type")
	@Convert(converter = FeedbackTypeAttributeConverter.class)
	FeedbackType feedbackType;	

	@Column(name="comments",length=500)
	private String commentsBody;
	
	

}
