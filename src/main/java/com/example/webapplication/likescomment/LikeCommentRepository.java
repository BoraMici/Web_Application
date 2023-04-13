package com.example.webapplication.likescomment;

import com.example.webapplication.likescomment.entity.LikesComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeCommentRepository extends JpaRepository<LikesComment ,Integer> {
    Optional<LikesComment> findByCommentIdAndUserId(Integer commentId, Long userId);

    @Query(value = "select * from likes_comment where comment_id=14 and is_like=true",nativeQuery = true)
    Object[] findLikedPosts();
}
