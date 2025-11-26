package com.binarybachelor.genlink.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.binarybachelor.genlink.dto.MessageRequestDto;
import com.binarybachelor.genlink.service.MessageService;


@Controller
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @MessageMapping("/send")
    public void sendMessage(@Payload MessageRequestDto messageRequestDto){
        logger.info("Received message: {}", messageRequestDto);
        try{
        messageService.sendMessage(messageRequestDto);
        }catch(Exception e){
            logger.error("Error deligate message: {}", e.getMessage());
        }
    }
}
