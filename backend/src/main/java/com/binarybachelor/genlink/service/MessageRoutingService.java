package com.binarybachelor.genlink.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.binarybachelor.genlink.enums.MessageType;
import com.binarybachelor.genlink.dto.MessageResponseDto;
import com.binarybachelor.genlink.entity.MessageEntity;

@Service
public  class MessageRoutingService{

  private static final Logger logger = LoggerFactory.getLogger(MessageRoutingService.class);
  private final SimpMessagingTemplate messagingTemplate;

  public MessageRoutingService(SimpMessagingTemplate messagingTemplate){
    this.messagingTemplate = messagingTemplate;
  }

  public void routeMessage(MessageEntity message){

    logger.info("Routing to user: {}", message.getReceiverId().getId());

    messagingTemplate.convertAndSendToUser(
      String.valueOf(message.getReceiverId().getId()),
      "/queue/messages",
      new MessageResponseDto(message.getSenderId().getId(), message.getReceiverId().getId(), message.getMessageType(),message.getContent(),message.getCreatedAt())
      );
  }
}