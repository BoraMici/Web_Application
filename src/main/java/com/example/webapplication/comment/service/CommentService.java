package com.example.webapplication.comment.service;

import com.example.webapplication.comment.dto.CommentDto;
import com.example.webapplication.comment.entity.Comment;
import com.example.webapplication.comment.repository.CommentRepository;
import com.example.webapplication.user.repository.UserRepository;
import com.example.webapplication.user.service.UserService;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }
    public Comment getSingleComment(int id) throws Exception {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()){
            comment.get().setNumberLike(comment.get().getLikesComments().size());
            return comment.get();
        }else throw new Exception("Comment does not exist");
    }
    public void deleteComment(int id) throws Exception {
        commentRepository.delete(getSingleComment(id));
    }
    public void editComment(int id, CommentDto comment) throws Exception {
      Comment newComment=getSingleComment(id);
        newComment.setComment(comment.getComment());
        newComment.setUser(userService.getSingleUser(comment.getUserId()));
        newComment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        commentRepository.save(newComment);
    }
}
