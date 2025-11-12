package com.binarybachelor.genlink.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import com.binarybachelor.genlink.entity.UserEntity;

@Entity
@Table(name = "user_profiles")
public class  UserProfileEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
  
    private String bio;
    private String avatarUrl;
    private String displayName;
    private String timeZone = "UTC";;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserProfileEntity(){}

    public Long getId(){return id;}
    
    public void setUser(UserEntity user){this.user = user;}
    public UserEntity getUser(){return user;}

    public void setBio(String bio){this.bio = bio;}
    public String getBio(){return bio;}

    public void setAvatarUrl(String avatarUrl){this.avatarUrl = avatarUrl;}
    public String getAvatarUrl(){return avatarUrl;}

    public void setDisplayName(String displayName){this.displayName = displayName;}
    public String getDisplayName(){return displayName;}

    public void setTimeZone(String timeZone){this.timeZone = timeZone;}
    public String getTimeZone(){return timeZone;}

    public LocalDateTime getUpdatedAt(){return updatedAt;}
}