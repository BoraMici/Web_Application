package com.example.webapplication.post.entity;

import com.example.webapplication.comment.entity.Comment;
import com.example.webapplication.image.entity.Image;
import com.example.webapplication.likespost.entity.LikesPost;
import com.example.webapplication.user.entity.User;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name ="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String  body;

    @Column(name = "status")
    private String status;

    @Column(name = "nrLikes")
    private int nrLikes;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",  referencedColumnName = "id")
    private List<LikesPost> likes=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "post_id",  referencedColumnName = "id")
    private List<Comment> comments=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "post_id",  referencedColumnName = "id")
    private List<Image> images=new ArrayList<>();
}
