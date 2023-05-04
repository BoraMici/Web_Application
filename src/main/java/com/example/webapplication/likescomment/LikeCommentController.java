package com.example.webapplication.likescomment;

import com.example.webapplication.comment.entity.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeCommentController {

    private final LikeCommentService likeCommentService;
    public LikeCommentController(LikeCommentService likeCommentService) {
        this.likeCommentService = likeCommentService;
    }
    @GetMapping("/{commentId}/comment-like/{userId}")
    private ResponseEntity<?> addLikesToComment(@PathVariable Integer commentId,@PathVariable Long userId) throws Exception {
       try {
           Comment updateComment= likeCommentService.likeOrUnLikeComment(commentId,userId);
           return ResponseEntity.ok(updateComment);
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }

    }



}
