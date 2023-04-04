package com.example.webapplication.user.entity;

import com.example.webapplication.comment.entity.Comment;
import com.example.webapplication.image.entity.Image;
import com.example.webapplication.likescomment.entity.LikesComment;
import com.example.webapplication.likespost.entity.LikesPost;
import com.example.webapplication.post.entity.Post;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "role")
    private String role;
    @Column(name = "created_at")
    private Timestamp createdAt;





}
