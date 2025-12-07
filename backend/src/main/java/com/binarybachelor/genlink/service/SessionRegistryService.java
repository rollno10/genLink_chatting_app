package com.binarybachelor.genlink.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.binarybachelor.genlink.entity.UserPresenceEntity;
import com.binarybachelor.genlink.enums.PresenceStatus;
import com.binarybachelor.genlink.repository.UserPresenceRepository;

@Service
public  class SessionRegistryService{

  private Map<Long, String> userSessionMap = new ConcurrentHashMap<>();
  private UserPresenceRepository userPresenceRepository;
  private static final Logger logger = LoggerFactory.getLogger(SessionRegistryService.class);

  public SessionRegistryService(UserPresenceRepository userPresenceRepository){
    this.userPresenceRepository = userPresenceRepository;
  }

  public void registerSession(long userId, String sessionId){

     userSessionMap.put(userId, sessionId);
    
     UserPresenceEntity userPresence = userPresenceRepository.findByUser_Id(userId).orElseThrow(() -> new RuntimeException("User presence not found"));
     userPresence.setStatus(PresenceStatus.ONLINE);
     userPresenceRepository.save(userPresence);
     logger.info("User {} is now online", userId);
    
  }

  public void unregisterSession(long userId){

    userSessionMap.remove(userId);
    
      UserPresenceEntity userPresence = userPresenceRepository.findByUser_Id(userId).orElseThrow(() -> new RuntimeException("User presence not found"));
      userPresence.setStatus(PresenceStatus.OFFLINE);
      userPresence.setLastSeenAt(LocalDateTime.now());
      userPresenceRepository.save(userPresence);
      logger.info("User {} is now offline", userId);
  }

  public boolean isUserOnline(long userId){
    return userSessionMap.containsKey(userId);
  }

  public Set<Long> getOnlineUsers(){
    return userSessionMap.keySet();
  }
}