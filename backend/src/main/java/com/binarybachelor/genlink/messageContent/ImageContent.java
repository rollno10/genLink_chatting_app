package com.binarybachelor.genlink.messageContent;

import com.binarybachelor.genlink.messageContent.Content;
import com.binarybachelor.genlink.enums.MessageType;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;

public  class ImageContent implements Content{

  @NotBlank(message = "Image URL cannot be blank")
  @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")
  private String imageUrl;
  @NotBlank(message = "MIME type cannot be blank")
  @Pattern(regexp = "^image/(png|jpg|jpeg|gif)", message = "MIME type must be (png,jpg,jpeg,gif) image type")
  private String mimeType;

  public ImageContent(@JsonProperty("imageUrl") String imageUrl,@JsonProperty("mimeType") String mimeType){
    this.imageUrl = imageUrl;
    this.mimeType = mimeType;
  }

  public String getImageUrl(){ return imageUrl;}
  public String getMimeType(){ return mimeType;}
  public String serialize(){
    return imageUrl + " " + mimeType;
  }
  @JsonIgnore
  public MessageType getMessageType(){
     return MessageType.IMAGE;
  }
}