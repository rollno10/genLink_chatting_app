package com.binarybachelor.genlink.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import com.binarybachelor.genlink.enums.MessageType;
import com.binarybachelor.genlink.dto.MessageResponseDto;
import com.binarybachelor.genlink.entity.MessageEntity;
import com.binarybachelor.genlink.enums.MessageStatus;
import com.binarybachelor.genlink.repository.MessageRepository;


@Service
public  class MessageRoutingService{

  private static final Logger logger = LoggerFactory.getLogger(MessageRoutingService.class);
  private final SimpMessagingTemplate messagingTemplate;
  private final MessageRepository messageRepository;

  public MessageRoutingService(SimpMessagingTemplate messagingTemplate,MessageRepository messageRepository){
    this.messagingTemplate = messagingTemplate;
    this.messageRepository = messageRepository;
  }

  public void routeMessage(MessageEntity message){

    logger.info("Routing to user: {}", message.getReceiverId().getId());

    messagingTemplate.convertAndSendToUser(
      String.valueOf(message.getReceiverId().getId()),
      "/queue/messages",
      new MessageResponseDto(message.getSenderId().getId(), message.getReceiverId().getId(), message.getMessageType(),message.getContent(),message.getCreatedAt())
      );
  }

  public void routePendingMessages(long userId){

    List<MessageEntity> pendingMessages = messageRepository.findByReceiverId_IdAndStatus(userId, MessageStatus.SENT);

    for(MessageEntity message : pendingMessages){
      routeMessage(message);
      message.setStatus(MessageStatus.DELIVERED);
      messageRepository.save(message);
    }
  }
}