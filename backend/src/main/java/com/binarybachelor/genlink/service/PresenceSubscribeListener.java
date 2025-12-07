package com.binarybachelor.genlink.service;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import java.security.Principal;
import org.springframework.stereotype.Component;

import com.binarybachelor.genlink.service.MessageRoutingService;

@Component
public class PresenceSubscribeListener implements ApplicationListener<SessionSubscribeEvent> {

    private final MessageRoutingService messageRoutingService;

    public PresenceSubscribeListener(MessageRoutingService messageRoutingService) {
        this.messageRoutingService = messageRoutingService;
    }

    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal principal = accessor.getUser();
        String destination = accessor.getDestination();

        if (principal != null && "/user/queue/messages".equals(destination)) {
            long userId = Long.parseLong(principal.getName());
            messageRoutingService.routePendingMessages(userId);
        }
    }
}
