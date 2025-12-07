package com.binarybachelor.genlink.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.binarybachelor.genlink.dto.MessageRequestDto;
import com.binarybachelor.genlink.entity.MessageEntity;
import com.binarybachelor.genlink.entity.UserEntity;
import com.binarybachelor.genlink.service.MessagePersistenceService;
import com.binarybachelor.genlink.service.MessageValidationService;
import com.binarybachelor.genlink.service.MessageRoutingService;
import com.binarybachelor.genlink.service.SessionRegistryService;
import com.binarybachelor.genlink.repository.MessageRepository;
import com.binarybachelor.genlink.enums.MessageStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  MessageService{

   private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
   @Autowired
   private MessageValidationService messageValidationService;
   @Autowired
   private MessagePersistenceService messagePersistenceService;
   @Autowired
   private MessageRoutingService messageRoutingService;
   @Autowired
   private SessionRegistryService sessionRegistryService;
   @Autowired   
   private MessageRepository messageRepository;

   public void sendMessage(MessageRequestDto messageRequestDto){

      logger.info("Sending between sender: {} reciever: {} type: {}", messageRequestDto.getSenderId(), messageRequestDto.getReceiverId(),messageRequestDto.getMessageType());

      try{
      long senderId = messageRequestDto.getSenderId();
      long receiverId = messageRequestDto.getReceiverId();

      UserEntity sender = messageValidationService.validateSenderExist(senderId);
      UserEntity receiver = messageValidationService.validateReciverExist(receiverId);

      if(sender != null && receiver != null) logger.info("Sender and Reciever are Validated");
        else logger.info("Invalid sender or reciever");

      MessageEntity message = messagePersistenceService.saveMessage(messageRequestDto, sender, receiver);

      if(message.getId() != 0) logger.info("Message saved in db");
          else logger.info("Cant save user message");

      if(sessionRegistryService.isUserOnline(receiverId)){
          
          messageRoutingService.routeMessage(message);
          message.setStatus(MessageStatus.DELIVERED);
          messageRepository.save(message);
      }
    }
      catch(Exception e){
          logger.error("Error server pipeline fails:{} " , e);
      }
  }
}