package com.example.webapplication.likespost;

import com.example.webapplication.post.entity.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikesPostController {
//servis me 2 parametra ne url / pathvariable post_id & user_id ex: //add-like/{post_id}/{user_id}
 private final LikesPostService likesPostService;

    public LikesPostController(LikesPostService likesPostService) {
        this.likesPostService = likesPostService;
    }

    @GetMapping("/{postId}/like/{userId}")
    public ResponseEntity<?> addLikeToPost(@PathVariable Integer postId ,@PathVariable Long userId) throws Exception {
        try {
            Post updatePost= likesPostService.likeOrUnLikePost(postId, userId);
            return ResponseEntity.ok(updatePost);
     }catch (Exception e){
         return ResponseEntity.badRequest().body(e.getMessage());
     }

    }
}
