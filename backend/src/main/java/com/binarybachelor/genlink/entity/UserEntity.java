package com.binarybachelor.genlink.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String mobile;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    private boolean isActive = true;

    private boolean isVerified = false;


    public UserEntity(){}

    public UserEntity(String username,String mobile,String password,String email){
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.email = email;
    }

    public long getId(){return id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getMobile(){return mobile;}
    public void setMobile(String mobile){this.mobile = mobile;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public LocalDateTime getCreatedAt(){return createdAt;}

    public LocalDateTime getUpdatedAt(){return updatedAt;}

    public boolean getIsActive(){return isActive;}
    public void setIsActive(boolean isActive){this.isActive = isActive;}

    public boolean getIsVerified(){return isVerified;}
    public void setIsVerified(boolean isVerified){this.isVerified = isVerified;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
}