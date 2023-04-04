package com.example.webapplication.likescomment.entity;

import com.example.webapplication.comment.entity.Comment;
import com.example.webapplication.post.entity.Post;
import com.example.webapplication.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name="likes_comment")
public class LikesComment {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "isLike")
    private boolean isLike;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @OneToOne(cascade = CascadeType.ALL)
    @ JoinColumn(name="user_id")
    private User user;
}
