package com.binarybachelor.genlink.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Column(nullable = false, unique = true)
    private String name;

    public Role(){}
    public Role(String name){
        this.name = name;
    }

    public long getId(){return role_id;}
    
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
}