package com.example.webapplication.post;

import com.example.webapplication.post.dto.PostDto;
import com.example.webapplication.post.entity.Post;
import com.example.webapplication.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class PostController {


   private final PostService postService;
   public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/posts")
    public Page<Post> getAllPosts(@RequestParam(defaultValue = "0") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "id") String sortBy){
        return postService.findAllPosts(pageNo,pageSize,sortBy);
    }
    @PostMapping("/post")
    public void createPost( @RequestParam("images") MultipartFile[] images,@RequestParam String title,
                            @RequestParam String body,
                            @RequestParam String status,
                            @RequestParam(name = "userId") Long userId) throws Exception {
        PostDto post=new PostDto(title,body,status,userId);
        postService.createPost(post,images);
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable int id) throws Exception {
        postService.deletePost(id);
    }
    @GetMapping("/post/{id}")
    public Post getSinglePost(@PathVariable int id) throws Exception {
       return postService.getSinglePost(id);
    }
    @PutMapping("/update-post/{id}")
    public void updatePost(@PathVariable int id,@RequestParam("images") MultipartFile[] images
            ,@RequestParam String title,
                           @RequestParam String body,
                           @RequestParam String status,
                           @RequestParam(name = "userId") Long userId) throws Exception {
        PostDto post=new PostDto(title,body,status,userId);
        postService.updatePost(post,id,images);

    }

}
