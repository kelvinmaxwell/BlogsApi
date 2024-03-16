package app.karimax.creswave;

import app.karimax.creswave.controller.PostController;
import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.PostDto;
import app.karimax.creswave.dao.SearchDto;
import app.karimax.creswave.exception.CustomBindingResultErrorResponse;

import app.karimax.creswave.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostControllerTests {

    @Mock
    private PostService postService;

    @Mock
    private CustomBindingResultErrorResponse errorResponseUtil;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPost_WithValidDto_ReturnsApiResponse() {
        PostDto postDto = new PostDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(postService.createPost(postDto)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = postController.createPost(postDto, bindingResult);

        assertEquals(200, response.getStatusCode().value());
        verify(postService).createPost(postDto);
    }

    @Test
    void createPost_WithInvalidDto_ReturnsErrorResponse() {
        PostDto postDto = new PostDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setStatus_code(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatus_desc("Validation failed.");
        when(errorResponseUtil.createResponseEntity(bindingResult)).thenReturn(errorResponse);

        ResponseEntity<ApiResponse> response = postController.createPost(postDto, bindingResult);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(errorResponse, response.getBody());
    }

    @Test
    void getPost_ReturnsApiResponse() {
        when(postService.getPosts(Pageable.unpaged())).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = postController.getPost(Pageable.unpaged());

        assertEquals(200, response.getStatusCodeValue());
        verify(postService).getPosts(Pageable.unpaged());
    }

    @Test
    void getSinglePost_WithValidId_ReturnsApiResponse() {
        long id = 1L;
        when(postService.getSinglePost(id)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = postController.getSinglePost(id);

        assertEquals(200, response.getStatusCodeValue());
        verify(postService).getSinglePost(id);
    }

    @Test
    void updatePost_WithValidDto_ReturnsApiResponse() {
        PostDto postDto = new PostDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(postService.updatePost(postDto)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = postController.updatePost(postDto, bindingResult);

        assertEquals(200, response.getStatusCodeValue());
        verify(postService).updatePost(postDto);
    }

    @Test
    void updatePost_WithInvalidDto_ReturnsErrorResponse() {
        // Arrange
        PostDto postDto = new PostDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setStatus_code(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatus_desc("Validation failed.");
        when(errorResponseUtil.createResponseEntity(bindingResult)).thenReturn(errorResponse);

        // Act
        ResponseEntity<ApiResponse> response = postController.updatePost(postDto, bindingResult);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Validation failed.", response.getBody().getStatus_desc());
    }

    @Test
    void deletePost_WithValidId_ReturnsApiResponse() {
        long id = 1L;
        when(postService.deletePost(id)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = postController.deletePost(id);

        assertEquals(200, response.getStatusCodeValue());
        verify(postService).deletePost(id);
    }

    @Test
    void searchBlogPosts_ReturnsApiResponse() {
        SearchDto searchDto = new SearchDto();
        Pageable pageable = Pageable.unpaged();
        when(postService.searchPost(searchDto.getKeyword_content(), pageable)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = postController.searchBlogPosts(searchDto, pageable);

        assertEquals(200, response.getStatusCodeValue());
        verify(postService).searchPost(searchDto.getKeyword_content(), pageable);
    }

    // Add similar tests for other controller methods

}