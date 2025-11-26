package com.binarybachelor.genlink.dto;

import com.binarybachelor.genlink.messageContent.Content;
import com.binarybachelor.genlink.enums.MessageType;
import java.time.LocalDateTime;

public  class MessageResponseDto{

  private long senderId;
  private long reciverId;
  private MessageType messageType;
  private Content content;
  private LocalDateTime sentAt;

  public MessageResponseDto(long senderId, long reciverId, MessageType messageType, Content content, LocalDateTime sentAt){
    this.senderId = senderId;
    this.reciverId = reciverId;
    this.messageType = messageType;
    this.content = content;
    this.sentAt = sentAt;
  }

  public long getSenderId(){
     return senderId;
  }
  public long getReciverId(){
     return reciverId;
  }
  public MessageType getMessageType(){
     return messageType;
  }
  public Content getContent(){
     return content;
  }
  public LocalDateTime getSentAt(){
     return sentAt;
  }
  
}