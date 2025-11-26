package com.binarybachelor.genlink.entity;

import com.binarybachelor.genlink.entity.UserEntity;
import com.binarybachelor.genlink.enums.MessageStatus;
import com.binarybachelor.genlink.enums.MessageType;
import com.binarybachelor.genlink.messageContent.Content;

import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "messages")
public class  MessageEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private UserEntity senderId;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private UserEntity receiverId;

    @Column(nullable = false)
    @Convert(converter = ContentConverter.class)
    private Content content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean isDeleted;

    public MessageEntity(){}

    public Long getId(){return id;}

    public void setSenderId(UserEntity senderId){this.senderId = senderId;}
    public UserEntity getSenderId(){return senderId;}

    public void setReceiverId(UserEntity receiverId){this.receiverId = receiverId;}
    public UserEntity getReceiverId(){return receiverId;}

    public void setContent(Content content){this.content = content;}
    public Content getContent(){return content;}

    public void setMessageType(MessageType messageType){this.messageType = messageType;}
    public MessageType getMessageType(){return messageType;}

    public void setStatus(MessageStatus status){this.status = status;}
    public MessageStatus getStatus(){return status;}

    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt;}

    public boolean getIsDeleted(){return isDeleted;}  
    public void setIsDeleted(boolean isDeleted){this.isDeleted = isDeleted;}

}