package com.binarybachelor.genlink.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import com.binarybachelor.genlink.entity.PresenceStatus;
import com.binarybachelor.genlink.entity.UserEntity;

@Entity
@Table(name = "user_presence")
public class  UserPresenceEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PresenceStatus status = PresenceStatus.OFFLINE;

    @UpdateTimestamp
    private LocalDateTime lastSeenAt;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user; 

    public UserPresenceEntity(){}

     public Long getId(){return id;}
    
     public LocalDateTime getLastSeenAt(){return lastSeenAt;}
    
    public void setUser(UserEntity user){this.user = user;}
    public UserEntity getUser(){return user;}

   public void setStatus(PresenceStatus status){this.status = status;}
   public PresenceStatus getStatus(){return status;}
}