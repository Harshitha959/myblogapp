package com.blogapp12.service.impl;

import com.blogapp12.entity.Post;
import com.blogapp12.payload.ListPostDto;
import com.blogapp12.payload.PostDto;
import com.blogapp12.repository.PostRepository;
import com.blogapp12.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//this is where we do database work of saving post to database
@Service
public class PostServiceImpl implements PostService {

//we will not use @Autowired inorder to do dependency injection
    //at the later stage in springboot constructor based dependency was introduced
    private PostRepository postRepository;

    //for dependency injection of library model mapper we create this variable , then do constructor based dependency injection
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,  ModelMapper modelMapper) { //this is constructor based dependency injection
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }
/* ************* 1st - verly long approach ****************
    @Override // this create method  will be called by controller, controller will be called from view , create() will use repository to save data
    public PostDto createPost(PostDto postDto) {
        //we have post object which is entity here we need to save this entity data with the help of
// postRepository layer thru const dependency inj
        Post post =  new Post();//post here is entity class, in this post object we copy dto content to entity class
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        //whatever data is saved in db , that data will be given/ return to entity class obj
      Post savedPost  = postRepository.save(post);

        //we cannot return entity object so , convert that to dto here
        PostDto dto = new PostDto();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        return dto;
  //summary - this method will take dto, convert the dto to entity, then save the entity to database
        // with repository layer, after saving save() gives saved content to entity, then again saved content will be converted to dto returned back
*/


    public PostDto createPost(PostDto postDto) {
        //to call the method (mapToEntity) which converts dto-->entity
        Post post = mapToEntity(postDto);
        //now save the converted object's data to database -- it returns entity obj, data gets saved in database with the help of repo layer
        Post savedPost = postRepository.save(post);
        //call the method (mapToDto) which converts entity--->dto
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

/* ****************** 2nd medium line approach *************
    //what ever we did above inside /*..this.. we can do it here with the help of 2 methods
    //1.mapToEntity(); -->converts dto to entity         2.mapToDto();--->converts entity to dto
    Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
// till here dto to entity conversion is done
    }

    PostDto mapToDto(Post post) {
        PostDto dto = new PostDto();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
        //till here entity to dto conversion is done
    } */

    //3rd approach using ModelMapper Library
    //here we have reduced the number of lines of code with modelMapper library ,which we did above with more number of lines of code
    Post mapToEntity(PostDto postDto){ // here we are taking dto obj, then in next line with the help of modelmapper we convert it into entity obj
      Post post = modelMapper.map(postDto, Post.class);// convert dto ---->Post entity class
      //map() is a method provided by the ModelMapper library that facilitates the transfer of data between objects of different types
      return post;
    }

    PostDto mapToDto(Post post){
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }


    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public ListPostDto fetchAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
   Sort sort =   sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending(); //it not only converts string to sort object, but also to asc & des

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> all = postRepository.findAll(pageable);

        List<Post> post = all.getContent();
        List<PostDto> postDto = post.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
   /*     System.out.println(all.getTotalPages());// if the records are 10 , page size is 5, total number of pages will be 2
        System.out.println(all.getTotalElements());
        System.out.println(all.isFirst());// if page no - 2 and has 12 elements this returns true becuase it's the last page
        System.out.println(all.isLast()); */
        ListPostDto listPostDto = new ListPostDto();
        listPostDto.setPostDto(postDto);
        listPostDto.setPageNumber(all.getNumber());
        listPostDto.setTotalPages(all.getTotalPages());
        listPostDto.setTotalElements(all.getTotalElements());
        listPostDto.setFirstPage(all.isFirst());
        listPostDto.setLastPage(all.isLast());
        return listPostDto;

    }

}

