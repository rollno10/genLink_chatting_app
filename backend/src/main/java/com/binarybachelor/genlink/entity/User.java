package com.binarybachelor.genlink.entity;

import jakarta.persistence.*;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String mobile;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User(){}

    public User(String username,String mobile,String password,Set<Role> roles){
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.roles = roles;
    }

    public long getId(){return user_id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getMobile(){return mobile;}
    public void setMobile(String mobile){this.mobile = mobile;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public Set<Role> getRoles(){return roles;}
    public void setRoles(Set<Role> roles){this.roles = roles;}

    public LocalDateTime getCreatedAt(){return createdAt;}
}