package com.example.webapplication.comment.entity;

import com.example.webapplication.likescomment.entity.LikesComment;
import com.example.webapplication.likespost.entity.LikesPost;
import com.example.webapplication.post.entity.Post;
import com.example.webapplication.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at" )
    private Timestamp updatedAt;

    @Column(name = "nrLikes")
    private int numberLike;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "post_id")
    private Post post;
}
