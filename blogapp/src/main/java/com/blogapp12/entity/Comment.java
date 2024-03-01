package com.blogapp12.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String name;
    private String message;
    @ManyToOne // Comment is many , post is one
    @JoinColumn(name= "post_id")//foreign key , joins two tables- post and comment
    private Post post;

}
