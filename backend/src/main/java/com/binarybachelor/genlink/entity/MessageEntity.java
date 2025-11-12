package com.binarybachelor.genlink.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import com.binarybachelor.genlink.entity.MessageType;
import com.binarybachelor.genlink.entity.MessageStatus;
import com.binarybachelor.genlink.entity.UserEntity;

@Entity
@Table(name = "messages")
public class  MessageEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private UserEntity senderId;

    @OneToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private UserEntity receiverId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean isDeleted = false;

    public MessageEntity(){}

    public Long getId(){return id;}

    public void setSender(UserEntity senderId){this.senderId = senderId;}
    public UserEntity getSender(){return senderId;}

    public void setReceiver(UserEntity receiverId){this.receiverId = receiverId;}
    public UserEntity getReceiver(){return receiverId;}

    public void setContent(String content){this.content = content;}
    public String getContent(){return content;}

    public void setType(MessageType type){this.type = type;}
    public MessageType getType(){return type;}

    public void setStatus(MessageStatus status){this.status = status;}
    public MessageStatus getStatus(){return status;}

    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt;}

    public boolean getIsDeleted(){return isDeleted;}  
    public void setIsDeleted(boolean isDeleted){this.isDeleted = isDeleted;}

}