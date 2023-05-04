package com.example.webapplication.likespost;

import com.example.webapplication.likespost.entity.LikesPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikePostRepository extends JpaRepository<LikesPost,Integer> {

    Optional<LikesPost> findByPostIdAndUserId(Integer postId, Long userId);

    @Query(value = "select * from likes_post where post_id=1 and is_like=1", nativeQuery = true)
    Object[] findLikedPosts();

}
