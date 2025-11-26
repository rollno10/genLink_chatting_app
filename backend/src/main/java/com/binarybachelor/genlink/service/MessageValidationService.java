package com.binarybachelor.genlink.service;

import com.binarybachelor.genlink.entity.UserEntity;

import com.binarybachelor.genlink.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.RuntimeException;


@Service
public class  MessageValidationService{

   @Autowired
   private UserRepository userRepository;
   
   public UserEntity validateSenderExist(long senderId){
     return userRepository.findById(senderId)
      .orElseThrow(() -> new RuntimeException("Sender not found with ID: " + senderId));
   }

   public UserEntity validateReciverExist(long reciverId){
      return userRepository.findById(reciverId)
         .orElseThrow(() -> new RuntimeException("Reciver not found with ID: " + reciverId));
   }
}