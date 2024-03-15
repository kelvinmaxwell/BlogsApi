package app.karimax.creswave.service;

import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.PostDto;
import org.springframework.data.domain.Pageable;


public interface PostService {
    ApiResponse createPost(PostDto postDto);

    ApiResponse updatePost(PostDto postDto);

    ApiResponse getPosts(Pageable pageable);

    ApiResponse getSinglePost(Long id);

    ApiResponse deletePost(Long id);



}
