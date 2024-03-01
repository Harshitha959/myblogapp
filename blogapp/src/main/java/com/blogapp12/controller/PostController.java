package com.blogapp12.controller;

import com.blogapp12.entity.Post;
import com.blogapp12.payload.ListPostDto;
import com.blogapp12.payload.PostDto;
import com.blogapp12.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    //for dependency injection of service layer we create variable postService
    private PostService postService;

    public PostController(PostService postService) { //here we are making constructor based dependency injection
        this.postService = postService;
    }

  //@RequestBody annotation tells Spring to bind the incoming JSON or XML data to the postDto parameter
  //http://localhost:8080/api/posts (the json content that is coming from this url, from the url the json content @RequestBody will fetch and put that to dto, if dto has content then  controller calls Service layer
   @PostMapping // we put ? because return types are different
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult) { // from postman data will come here to postDto object of PostDto class
    /*   if(postDto.getTitle().length() < 3){
            return new ResponseEntity<>("Title should be atleast 3 characters", HttpStatus.INTERNAL_SERVER_ERROR);
        }
       if(postDto.getDescription().length()<5) {
           return new ResponseEntity<>("Description should atleast be 5 characters", HttpStatus.INTERNAL_SERVER_ERROR);
       }*/

       //this is how validation should be done in our project
       //1. add the validator library 2.use annotations in dto class and @Validate- without this annotation validation is not possible
       //3.add binding result and create obj so ,it automatically captures the error message in the object
       //4.now use hasError()-->boolean return type checks if error is there in it or not,
       //***WE USE THESE TWO BELOW METHODS BECAUSE DYNAMICALLY THE ERROR SHOULD BE RETURNED TO CLIENT***(every time we don't give "the title should be 3 char" etc so)
       // 4.1then use getFieldError() --> to get that particular field error
       // 4.2 and then use  getDefaultMessage()--> if message is not give in @Size(), it retrieves default error message in postman

       if(bindingResult.hasErrors()){
           return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

     PostDto  dto = postService.createPost(postDto); //it will call service class method and data will be returned to dto class
     return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    /*
     @DeleteMapping("/{postId}") INCASE THIS NAME IS DIFFERENT AND ID NAME IS DIFFERENT YOU HAVE TO DO THIS
    public ResponseEntity<String> deletePost(@PathVariable("postId") long id){ //to read id from url we need to write @PathVariable
        postService.deletePost(id);
    }*/

    //http://localhost:8080/api/posts/2
    @DeleteMapping("/{id}")// BUT IN THIS WE DONT HAVE TO BECAUSE IT'S SAME
    public ResponseEntity<String> deletePost(@PathVariable long id){ //to read id from url we need to write @PathVariable
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);
        //here after pasting this url in postman then send , then post gets deleted , after publishing , copy url and browse,
        //IF YOU MAKE IT SAVE AS EXAMPLE YOU WILL GET RESPONSE IN THAT PUBLISHED DOCUMENTATION (Example Request, AND Example Response)
    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=6&sortBy=title
    @GetMapping
    public ResponseEntity<ListPostDto> fetchAllPosts(
            @RequestParam (name="pageNo", defaultValue = "0", required = false) int pageNo, //page no =0 is 1st page , [[if there are 12 records suppose if i give page no=0, page size = 5 , 5 elements per page ,pg0 and  pg1 has 5 + 5 elements, pg2 will have 2 records
            @RequestParam(name="pageSize", defaultValue = "5", required = false) int pageSize, //if given size=5--> then per page 5 elements
            @RequestParam(name="sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name="sortDir", defaultValue = "asc", required = false) String sortDir
    ){

        ListPostDto listPostDtos = postService.fetchAllPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(listPostDtos, HttpStatus.OK);

    }
}
