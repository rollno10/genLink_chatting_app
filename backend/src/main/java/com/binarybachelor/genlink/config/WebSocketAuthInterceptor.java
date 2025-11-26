package com.binarybachelor.genlink.config;

import java.security.Principal;
import java.util.Map;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.binarybachelor.genlink.security.JwtService;
import com.binarybachelor.genlink.security.CustomUserDetailsService;

@Configuration
public class  WebSocketAuthInterceptor implements HandshakeInterceptor{

  private static final Logger logger = LoggerFactory.getLogger(WebSocketAuthInterceptor.class);
  private final JwtService jwtService;
  private final CustomUserDetailsService customUserDetailsService;

  public WebSocketAuthInterceptor(JwtService jwtService, CustomUserDetailsService customUserDetailsService){
    this.jwtService = jwtService;
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes){

    var headers = request.getHeaders();
    String authHeader = headers.getFirst("authorization");
    URI uri = request.getURI();
    String query = uri.getQuery();

    String token = null;
    if(authHeader != null && authHeader.startsWith("Bearer ")){
      token = authHeader.substring(7);
      logger.info("The  JWT token : ",token);
    }
    if (token == null && query != null && query.contains("token=")) {
        int start = query.indexOf("token=") + 6;
        int end = query.indexOf("&", start);
        token = (end == -1) ? query.substring(start) : query.substring(start, end);
        logger.info("JWT from query: {}", token);
    }

    if (token == null) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED); 
        return false; 
    }

    String mobile = jwtService.extractMobile(token);
    String id = jwtService.extractId(token);

    if (mobile == null || mobile.isEmpty()) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return false;
    }
    
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(mobile);

    if (userDetails == null) {
        logger.info("User not found for mobile: ", mobile); 
        return false; 
    }
    else logger.info("User mobile" , mobile);
    
    if (!jwtService.validateToken(token,userDetails)) {
      response.setStatusCode(HttpStatus.UNAUTHORIZED);
      return false;
    }

    Principal principal = () -> id;
    
    attributes.put("principal",principal);
    attributes.put("id",id);
    attributes.put("mobile",mobile);

    logger.info("Principal name set to: {}", principal.getName());
    return true;
  }

  @Override
  public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception){}
}