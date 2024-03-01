package com.blogapp12.payload;

import lombok.Data;

import java.util.List;

@Data
public class ListPostDto {

    private List<PostDto> postDto;

    private int totalPages;
    private int pageNumber;
    private long totalElements;
    private boolean firstPage;
    private boolean lastPage;
}
