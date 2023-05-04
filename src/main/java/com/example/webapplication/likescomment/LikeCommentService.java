package com.example.webapplication.likescomment;

import com.example.webapplication.comment.entity.Comment;
import com.example.webapplication.comment.repository.CommentRepository;
import com.example.webapplication.comment.service.CommentService;
import com.example.webapplication.likescomment.entity.LikesComment;
import com.example.webapplication.likespost.entity.LikesPost;
import com.example.webapplication.user.entity.User;
import com.example.webapplication.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCommentService {
    private final LikeCommentRepository likeCommentRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final UserRepository userRepository;

    public LikeCommentService(LikeCommentRepository likeCommentRepository, CommentRepository commentRepository, CommentService commentService,UserRepository userRepository) {
        this.likeCommentRepository = likeCommentRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
        this.userRepository=userRepository;
    }

    public Comment likeOrUnLikeComment(Integer commentId, Long userId) throws Exception {
        Comment comment=commentService.getSingleComment(commentId);
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("User not found with this id "+  userId));

        Optional<LikesComment> likeComments=likeCommentRepository.findByCommentIdAndUserId(commentId, userId);
        if (likeComments.isPresent()){
            likeComments.get().setLike(!likeComments.get().isLike());
            likeCommentRepository.findLikedPosts();
            likeCommentRepository.save(likeComments.get());
            comment.setNumberLike(comment.getLikesComments().size());
        }else {
            likeCommentRepository.save(new LikesComment(comment,user));
            likeCommentRepository.findLikedPosts();
            comment.setNumberLike(comment.getNumberLike()+1);
        }
      return commentRepository.save(comment);
    }
}
