package com.blogapp12.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    @NotEmpty //--->1.cannot take null value 2.cannot submit empty data
    @Size(min= 3, message = "Title should be more than 3 characters")
    private String title;
    @NotEmpty
    private String description;
    private String content;

    //to validate email just use @Email annotation
    @Email
    private String email;
    //NOTE- SIZE ANNOTATION ONLY WORKS ON STRING, does'nt work on numbers--like--int, long etc(for that use @min @max
    @Size(min=10, max=10, message = "mobile number should be 10 digits")
    private String mobile;
}
