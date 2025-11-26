package com.binarybachelor.genlink.messageContent;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.binarybachelor.genlink.messageContent.TextContent;
import com.binarybachelor.genlink.messageContent.ImageContent;
import com.binarybachelor.genlink.messageContent.EmojiContent;
import com.binarybachelor.genlink.enums.MessageType;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "messageType"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = TextContent.class, name = "TEXT"),
  @JsonSubTypes.Type(value = ImageContent.class, name = "IMAGE"),
  @JsonSubTypes.Type(value = EmojiContent.class, name = "EMOJI")
})
public interface  Content {
  String serialize();
  MessageType getMessageType();
}