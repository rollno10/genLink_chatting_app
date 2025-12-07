package com.binarybachelor.genlink.service;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import java.security.Principal;
import org.springframework.stereotype.Component;

import com.binarybachelor.genlink.service.SessionRegistryService;

@Component
public class  PresenceConnectListener  implements ApplicationListener<SessionConnectEvent>{

  private final SessionRegistryService sessionRegistryService;

  public PresenceConnectListener(SessionRegistryService sessionRegistryService){
    this.sessionRegistryService = sessionRegistryService;
  }

  @Override
  public void onApplicationEvent(SessionConnectEvent event){

    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    String sessionId = accessor.getSessionId();
    Principal principal = accessor.getUser();

    try{
      long userId = Long.parseLong(principal.getName());
      sessionRegistryService.registerSession(userId, sessionId);
    }
    catch(Exception e){
       System.out.println("Error while connecting user: "+e.getMessage());
    }
  }
}