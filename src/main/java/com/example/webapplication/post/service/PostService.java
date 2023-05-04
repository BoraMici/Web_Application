package com.example.webapplication.post.service;

import com.example.webapplication.comment.dto.CommentDto;
import com.example.webapplication.comment.entity.Comment;
import com.example.webapplication.comment.repository.CommentRepository;
import com.example.webapplication.image.service.ImageService;
import com.example.webapplication.post.dto.PostDto;
import com.example.webapplication.post.entity.Post;
import com.example.webapplication.post.respository.PostRepository;
import com.example.webapplication.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NonUniqueResultException;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final ImageService imageService;

    public PostService(PostRepository postRepository, UserService userService, CommentRepository commentRepository, ImageService imageService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.imageService = imageService;
    }


    public void createPost(PostDto post, MultipartFile[] images) throws Exception {
        Post post1 =new Post();
        post1.setTitle(post.getTitle());
        post1.setBody(post.getBody());
        post1.setStatus(post.getStatus());
        post1.setUser(userService.getSingleUser(post.getUserId()));
        postRepository.save(post1);
        imageService.attachImage(post1,images);
    }

    public Page<Post> findAllPosts(Integer pageNr,Integer pageSize, String sortBy) {
        Pageable pageable= PageRequest.of(pageNr,pageSize, Sort.by(sortBy).ascending());
        Page<Post> postR = postRepository.findAll(pageable);
        postR.getContent();
        if (postR.getContent().size() > 0) {
            for (int i = 0; i < postR.getContent().size(); i++) {
                postR.getContent().get(i).setNrLikes(postR.getContent().get(i).getLikes().size());
            }
        }

        return postR;
    }

    public void deletePost(int id) throws Exception {
        postRepository.deleteById(getSinglePost(id).getId());
    }

    public Post getSinglePost(int id) throws Exception {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()){
            post.get().setNrLikes(post.get().getLikes().size());
            return post.get();
        }else throw new Exception("Post does not exist");
    }

    public void addComment(CommentDto comment, int postId) throws Exception {
        Post post = getSinglePost(postId);
        Comment newComment=new Comment();
        newComment.setPost(post);
        newComment.setUser(userService.getSingleUser(comment.getUserId()));
        newComment.setComment(comment.getComment());
        newComment.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        commentRepository.save(newComment);
    }
    public void updatePost(PostDto post, int id, MultipartFile[] images) throws Exception {
        Post updatePost=getSinglePost(id);
        updatePost.setTitle(post.getTitle());
        updatePost.setBody(post.getBody());
        updatePost.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        postRepository.save(updatePost);

        imageService.attachImage(updatePost,images);

    }
//
}
