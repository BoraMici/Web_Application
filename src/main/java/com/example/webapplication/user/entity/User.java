package com.example.webapplication.user.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "role")
    private String role;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;



}
