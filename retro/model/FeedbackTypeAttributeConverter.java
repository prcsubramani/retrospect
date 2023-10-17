package com.sys.scrum.retro.model;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply=true)
public class FeedbackTypeAttributeConverter implements AttributeConverter<FeedbackType, String> {

  public String convertToDatabaseColumn( FeedbackType value ) {
    if ( value == null ) {
      return null;
    }

    return value.name();
  }

  public FeedbackType convertToEntityAttribute( String value ) {
    if ( value == null ) {
      return null;
    }

    return FeedbackType.valueOf( value );
  }

}