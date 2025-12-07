package com.binarybachelor.genlink.service;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import java.security.Principal;
import org.springframework.stereotype.Component;

import com.binarybachelor.genlink.service.SessionRegistryService;

@Component
public class  PresenceDisconnectListener implements ApplicationListener<SessionDisconnectEvent>{

   private final SessionRegistryService sessionRegistryService;

   public PresenceDisconnectListener(SessionRegistryService sessionRegistryService){
     this.sessionRegistryService = sessionRegistryService;
   }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event){

      StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
      Principal principal = accessor.getUser();
      
      try{
        long userId = Long.parseLong(principal.getName());
        sessionRegistryService.unregisterSession(userId);
      }
      catch(Exception e){
         System.out.println("Error while disconnecting user: "+e.getMessage());
      }
    }
}