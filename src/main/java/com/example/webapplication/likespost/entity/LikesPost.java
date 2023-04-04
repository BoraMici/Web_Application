package com.example.webapplication.likespost.entity;

import com.example.webapplication.post.entity.Post;
import com.example.webapplication.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "likes_post")
public class LikesPost {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "isLike")
    private boolean isLike;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;


}
