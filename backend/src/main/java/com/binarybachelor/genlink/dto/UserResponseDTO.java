package com.binarybachelor.genlink.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserResponseDTO{
    private final Long  id;
    private final String username;
    private final String mobile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token ;

    public UserResponseDTO(Long id, String username, String mobile) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
    }

        public UserResponseDTO(Long id, String username, String mobile, String token) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
        this.token = token;
    }

    public Long getId(){return id;}
    public String getUsername(){return username;}
    public String getMobile(){return mobile;}
    public String getToken(){return token;}
}