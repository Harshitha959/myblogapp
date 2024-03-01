package com.blogapp12.controller;

import com.blogapp12.payload.CommentDto;
import com.blogapp12.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService; // we are calling this class's method so doing dependency inj with the help of @AllArgsConstructor

    //http://localhost:8080/api/comments/1
    @PostMapping("/{postId}") //bcz of pathvaribale we giv this
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto, //to convert/copy json-->to-->dto
            @PathVariable long postId) { //to copy url data into it
        //from above code whatever data fetched from postman  will be given to below method
        CommentDto dto = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostid(@PathVariable long postId){
        List<CommentDto>  commentsDtos = commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(commentsDtos, HttpStatus.OK);
    }
}
