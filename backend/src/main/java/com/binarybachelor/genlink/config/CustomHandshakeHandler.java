package com.binarybachelor.genlink.config;

import java.security.Principal;
import java.util.Map;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
  @Override
  protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
    // Expect the interceptor to have placed a Principal in attributes
    Object p = attributes.get("principal");
    if (p instanceof Principal) {
      return (Principal) p; // This becomes the WebSocket session Principal
    }
    return request.getPrincipal(); // Fallback (often null in SockJS)
  }
}
