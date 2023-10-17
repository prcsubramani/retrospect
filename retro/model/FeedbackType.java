package com.sys.scrum.retro.model;


public enum FeedbackType {
	
	POSTIVE("Positive"),NEGATIVE("Negative"),IDEA("Idea"),PRAISE("Praise");
	
	private String code;
	private FeedbackType(String code) {
		this.code=code;
	}
	
	public String getCode() {
		return code;  
	}
	
	public static FeedbackType findByCode(String code) {
		for(FeedbackType type : values()) {
			if(type.getCode().equals(code)) {
				return type;
			}			
		}
		return null;	
	}
	

}
