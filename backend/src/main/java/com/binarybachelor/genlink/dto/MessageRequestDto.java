package com.binarybachelor.genlink.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;

import com.binarybachelor.genlink.enums.MessageType;
import com.binarybachelor.genlink.messageContent.Content;
import com.binarybachelor.genlink.messageContent.TextContent;
import com.binarybachelor.genlink.messageContent.ImageContent;
import com.binarybachelor.genlink.messageContent.EmojiContent;

public class MessageRequestDto{

  @NotNull(message = "Sender ID is required")
  private long senderId;
  @NotNull(message = "Receiver ID is required")
  private long receiverId;
  @NotNull(message = "Message type is required")
  private MessageType messageType;

  @JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
    property = "messageType"
  )
  @JsonSubTypes({
    @JsonSubTypes.Type(value = TextContent.class, name = "TEXT"),
    @JsonSubTypes.Type(value = ImageContent.class, name = "IMAGE"),
    @JsonSubTypes.Type(value = EmojiContent.class, name = "EMOJI")
  })
  private Content content;

  public void setSenderId(long senderId){this.senderId = senderId;}
  public long getSenderId(){return senderId;}

  public void setReceiverId(long receiverId){this.receiverId = receiverId;}
  public long getReceiverId(){return receiverId;}

  public void setMessageType(MessageType messageType){this.messageType = messageType;}
  public MessageType getMessageType(){return messageType;}

  public Content getContent(){return content;}
  public void setContent(Content content){this.content = content;}
}