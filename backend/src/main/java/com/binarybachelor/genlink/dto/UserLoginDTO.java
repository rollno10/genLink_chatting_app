package com.binarybachelor.genlink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class UserLoginDTO{

    @NotBlank(message = "Mobile Number is required")
    @Size(min = 10, message = "Mobile number should be minimum of 10 character")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Mobile Number")
    private String mobile;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should be minimum of 6 character")
    private String password;

    public String getMobile(){return mobile;}
    public void setMobile(String mobile){this.mobile = mobile;}

    public String getPassword(){return password;}
    public void setPassword(String password){
        this.password = password;
    }

}