package com.example.webapplication.post.dto;

import com.example.webapplication.image.entity.Image;
import com.example.webapplication.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class PostDto {
    private String title;
    private String body;
    private String status;
    private Long userId;





}
