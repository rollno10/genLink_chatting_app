package com.binarybachelor.genlink.service;

import com.binarybachelor.genlink.entity.MessageEntity;
import com.binarybachelor.genlink.repository.MessageRepository;
import com.binarybachelor.genlink.dto.MessageRequestDto;
import com.binarybachelor.genlink.enums.MessageStatus;
import com.binarybachelor.genlink.entity.UserEntity;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public  class MessagePersistenceService{

  private static final Logger logger = LoggerFactory.getLogger(MessagePersistenceService.class);
  private MessageRepository messageRepository;

  public MessagePersistenceService(MessageRepository messageRepository){
    this.messageRepository = messageRepository;
  }
  
  public MessageEntity saveMessage (MessageRequestDto messageRequestDto,UserEntity sender,UserEntity receiver){

    MessageEntity message = new MessageEntity();
    message.setSenderId(sender);
    message.setReceiverId(receiver);
    message.setContent(messageRequestDto.getContent());
    message.setMessageType(messageRequestDto.getMessageType());
    message.setStatus(MessageStatus.SENT);
    message.setIsDeleted(false);
    
    return messageRepository.save(message);
  }
}