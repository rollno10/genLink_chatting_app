package com.binarybachelor.genlink.messageContent;

import com.binarybachelor.genlink.messageContent.Content;
import com.binarybachelor.genlink.enums.MessageType;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

public  class EmojiContent implements Content{

  @NotBlank(message = "Emoji content cannot be blank")
  @Size(max = 10, message = "Emoji content cannot exceed 10 characters")
  private String emoji;

  public EmojiContent(@JsonProperty("emoji") String emoji){
    this.emoji = emoji;
  }

  public String getEmoji(){ return emoji;}
  public String serialize(){
    return emoji;
  }
  @JsonIgnore
  public MessageType getMessageType(){
    return MessageType.EMOJI;
  }
}