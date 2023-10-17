package com.sys.scrum.retro.model;



import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;          
import lombok.NoArgsConstructor; 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrospectFeedbackPk implements java.io.Serializable {   

	/**
	 * 
	 */
	private static final long serialVersionUID = -8303637753428771976L;
	
	private Long id;

	private String teamMemberCode;
	
	private String  retroFeedback;
	

	

	@Override
	public int hashCode() {
		return Objects.hash(retroFeedback, teamMemberCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RetrospectFeedbackPk other = (RetrospectFeedbackPk) obj;
		return  Objects.equals(retroFeedback, other.retroFeedback) && Objects.equals(teamMemberCode, other.teamMemberCode);
	}	



}
