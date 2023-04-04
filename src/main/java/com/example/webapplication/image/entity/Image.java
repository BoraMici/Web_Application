package com.example.webapplication.image.entity;

import com.example.webapplication.post.entity.Post;
import com.example.webapplication.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "image_url" )
    private String imageUrl;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "post_id")
    private Post post;
}