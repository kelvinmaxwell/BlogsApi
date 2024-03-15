package app.karimax.creswave.controller;

import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.PostDto;
import app.karimax.creswave.dao.SearchDto;
import app.karimax.creswave.exception.CustomBindingResultErrorResponse;
import app.karimax.creswave.model.Post;
import app.karimax.creswave.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;
    private final CustomBindingResultErrorResponse errorResponseUtil;


    @PostMapping
    public ResponseEntity<ApiResponse> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {  //check any existing errors and calls errorResponseUtil to clean and format errors

            return ResponseEntity.ok(errorResponseUtil.createResponseEntity(bindingResult));

        }
        return ResponseEntity.ok(postService.createPost(postDto)); //success response
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getPost(Pageable pageable) {
        return ResponseEntity.ok(postService.getPosts(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getSinglePost(@PathVariable("id") Long id) {

        return ResponseEntity.ok(postService.getSinglePost(id));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updatePost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return ResponseEntity.ok(errorResponseUtil.createResponseEntity(bindingResult));

        }
        return ResponseEntity.ok(postService.updatePost(postDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("id") Long id) {


        return ResponseEntity.ok(postService.deletePost(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchBlogPosts(@RequestBody SearchDto searchDto,Pageable pageable) {

        return ResponseEntity.ok( postService.searchPost(searchDto.getKeyword_content(),pageable));
    }
}
