package com.blogapp12.payload;

import lombok.Data;

@Data
public class CommentDto {

    private long Id;
    private String name;
    private String message;
}
