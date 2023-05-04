package com.example.webapplication.likespost;

import com.example.webapplication.likespost.entity.LikesPost;
import com.example.webapplication.post.entity.Post;
import com.example.webapplication.post.respository.PostRepository;
import com.example.webapplication.post.service.PostService;
import com.example.webapplication.user.entity.User;
import com.example.webapplication.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class LikesPostService {
    private final LikePostRepository likePostRepository;
    private final PostRepository postRepository;
    private final PostService postService;
    private final UserRepository userRepository;

    public LikesPostService(LikePostRepository likePostRepository, PostRepository postRepository, PostService postService, UserRepository userRepository) {
        this.likePostRepository = likePostRepository;
        this.postRepository = postRepository;
        this.postService = postService;
        this.userRepository = userRepository;
    }

    public Post likeOrUnLikePost(Integer postId, Long userId) throws Exception {
        Post post=postService.getSinglePost(postId);
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("User not found with this id "+  userId));

        Optional<LikesPost> like = likePostRepository.findByPostIdAndUserId(postId, userId);
        if (like.isPresent()){
            like.get().setLike(!like.get().isLike());
            likePostRepository.findLikedPosts();
            likePostRepository.save(like.get());
            post.setNrLikes(post.getLikes().size());
        }else {
            likePostRepository.save(new LikesPost(post,user));
            likePostRepository.findLikedPosts();
            post.setNrLikes(post.getNrLikes()+1);
        }
        return postRepository.save(post);
    }
}
