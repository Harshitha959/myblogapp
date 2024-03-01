package com.blogapp12.repository;

import com.blogapp12.entity.Comment;
import com.blogapp12.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //returns entity class obj , and this is userdefined method to find all comments, we are passing postId, only for that particular Postid you fetch comments eetara
    List<Comment> findCommentByPostId(long PostId);
}
