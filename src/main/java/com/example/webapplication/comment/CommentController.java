package com.example.webapplication.comment;

import com.example.webapplication.comment.dto.CommentDto;
import com.example.webapplication.comment.entity.Comment;
import com.example.webapplication.comment.service.CommentService;
import com.example.webapplication.post.service.PostService;
import org.springframework.web.bind.annotation.*;


@RestController
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;


    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }
    @PostMapping("/add-comment/{postId}")
    public void addComment(@RequestBody CommentDto comment , @PathVariable int postId) throws Exception {
        postService.addComment(comment,postId);
    }
    @GetMapping("/comment/{id}")
    public Comment getSingleComment(@PathVariable int id) throws Exception {
        return commentService.getSingleComment(id);
    }
    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable int id) throws Exception {
        commentService.deleteComment(id);
    }
    @PutMapping("/edit-comment/{id}")
    public void editComment(@PathVariable int id,@RequestBody CommentDto comment) throws Exception {
         commentService.editComment(id,comment);

    }
}
