package com.binarybachelor.genlink.service;

import com.binarybachelor.genlink.entity.MessageEntity;
import com.binarybachelor.genlink.enums.MessageStatus;
import com.binarybachelor.genlink.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageStatusService{

  private final MessageRepository messageRepository;

  public MessageStatusService(MessageRepository messageRepository){
    this.messageRepository = messageRepository;
  }

  public void setMessageStatus(long messageId, MessageStatus status){
     MessageEntity message = messageRepository.findById(messageId).orElseThrow(() -> new RuntimeException("Message not found"));
     message.setStatus(status);
     messageRepository.save(message);
  }
}