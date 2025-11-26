package com.binarybachelor.genlink.messageContent;

import com.binarybachelor.genlink.messageContent.Content;
import com.binarybachelor.genlink.enums.MessageType;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class  TextContent implements Content{

  @NotBlank(message = "Text content cannot be blank")
  @Size(max = 500, message = "Text content cannot exceed 500 characters")
  private String text;

  public TextContent(@JsonProperty("text") String text){
    this.text = text;
  }
  public String getText(){ return text;}
  public String serialize(){
    return text;
  }
  @JsonIgnore
  public MessageType getMessageType(){
     return MessageType.TEXT;
  }
}