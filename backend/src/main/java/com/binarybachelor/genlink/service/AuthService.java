package com.binarybachelor.genlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.binarybachelor.genlink.dto.UserLoginDTO;
import com.binarybachelor.genlink.dto.UserRegisterDTO;
import com.binarybachelor.genlink.dto.UserResponseDTO;
import com.binarybachelor.genlink.entity.UserEntity;
import com.binarybachelor.genlink.repository.UserRepository;
import com.binarybachelor.genlink.security.JwtService;
import com.binarybachelor.genlink.entity.UserPresenceEntity;
import com.binarybachelor.genlink.enums.PresenceStatus;
import com.binarybachelor.genlink.repository.UserPresenceRepository;

@Service
public class AuthService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPresenceRepository userPresenceRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwt;

    public UserResponseDTO registerUser(UserRegisterDTO request){
        
        if(userRepository.findByMobile(request.getMobile()).isPresent()){
            throw new RuntimeException("User already exist with the mobile");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setMobile(request.getMobile());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        UserEntity saveUser = userRepository.save(user);

        UserPresenceEntity userPresence = new UserPresenceEntity();
        userPresence.setStatus(PresenceStatus.OFFLINE);
        userPresence.setUser(saveUser);
        userPresenceRepository.save(userPresence);

        return new UserResponseDTO(saveUser.getId(),saveUser.getUsername(), saveUser.getMobile(),saveUser.getEmail());
    }
    
    public UserResponseDTO loginUser(UserLoginDTO request){

        UserEntity user = userRepository.findByMobile(request.getMobile())
        .orElseThrow(() -> new RuntimeException("User not Found with the Mobile "+request.getMobile()));
    
        boolean isPasswordValid = passwordEncoder.matches(request.getPassword(),user.getPassword());
        if(!isPasswordValid){
            throw new RuntimeException("invalid password");
        }

        String token = jwt.generateToken(user.getId(),user.getMobile());

        return new UserResponseDTO(user.getId(), user.getUsername(), user.getMobile(), token);
    }

}