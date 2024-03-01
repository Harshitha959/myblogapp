package com.blogapp12.service.impl;

import com.blogapp12.entity.Comment;
import com.blogapp12.entity.Post;
import com.blogapp12.payload.CommentDto;
import com.blogapp12.repository.CommentRepository;
import com.blogapp12.repository.PostRepository;
import com.blogapp12.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor// previously for understanding purpose in post class we had created constructor explicitly but now i here we have not created becuase of this annotation , constructor gets created bcz of @AllArgsConst
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findCommentByPostId(postId);
        List<CommentDto> commentsDtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return commentsDtos;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        //we have to find that post by id, on that post the comment should be added
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();//convert optional obj to entity obj

        Comment comment = mapToEntity(commentDto);//convert the commentDto to Comment Entity so that you can perform save opertation in db
        comment.setPost(post);//by specifying this we tell this above comment is for this post, becuase there are many posts
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    Comment mapToEntity(CommentDto dto){
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;
    }

    CommentDto mapToDto(Comment comment){
      return  modelMapper.map(comment, CommentDto.class);//shortcut for direct return

    }
}
