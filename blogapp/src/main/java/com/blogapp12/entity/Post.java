package com.blogapp12.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")
@Data //it is used here to set setters and getters for variables
@NoArgsConstructor //this must be created explicitly because we are created @AllArgsConstructor
@AllArgsConstructor //for the below written fields/variables we have to generate a constructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String content;

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();
}
