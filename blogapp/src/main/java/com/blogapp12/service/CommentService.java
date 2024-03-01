package com.blogapp12.service;

import com.blogapp12.payload.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllCommentsByPostId(long id);

    CommentDto createComment(CommentDto commentDto, long postId);


}
