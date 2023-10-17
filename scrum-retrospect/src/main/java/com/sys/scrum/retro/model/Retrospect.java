package com.sys.scrum.retro.model;

import java.util.ArrayList;
import java.util.Date;          
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString(exclude = "scrumTeamMembers")
@EqualsAndHashCode(exclude = "scrumTeamMembers")
@Entity
@Table(name = "scrum_retro_info")
public class Retrospect {

	@Id
	@Column(name = "retro_name", unique = true, nullable = false, length = 30)
	String retroName;
	

	@Column(name = "retro_summary", length = 100)
	String summary;

	@Column(name = "retro_date", nullable = false)
	@Temporal(TemporalType.DATE)
	Date retrospectiveDate;

	@OneToMany(mappedBy = "retrospect", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ScrumTeamMember> scrumTeamMembers = new ArrayList<>();

	@OneToMany(mappedBy = "retroFeedback", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<RetrospectFeedback> feedbackItems = new ArrayList<>();

}
