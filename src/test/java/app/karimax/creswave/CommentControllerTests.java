package app.karimax.creswave;

import app.karimax.creswave.controller.CommentController;
import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.CommentDto;
import app.karimax.creswave.exception.CustomBindingResultErrorResponse;
import app.karimax.creswave.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class CommentControllerTests {

    @Mock
    private CommentService commentService;

    @Mock
    private CustomBindingResultErrorResponse errorResponseUtil;

    @InjectMocks
    private CommentController commentController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void createComment_WithValidDto_ReturnsApiResponse() {
        CommentDto commentDto = new CommentDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(commentService.createComment(commentDto)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = commentController.createComment(commentDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(commentService).createComment(commentDto);
    }

    @Test
    void createComment_WithInvalidDto_ReturnsErrorResponse() {
        CommentDto commentDto = new CommentDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setStatus_code(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatus_desc("Validation failed.");
        when(errorResponseUtil.createResponseEntity(bindingResult)).thenReturn(errorResponse);

        ResponseEntity<ApiResponse> response = commentController.createComment(commentDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals(errorResponse, response.getBody());
    }

    @Test
    void getComments_ReturnsApiResponse() {
        when(commentService.getComments(Pageable.unpaged())).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = commentController.getComments(Pageable.unpaged());

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(commentService).getComments(Pageable.unpaged());
    }

    @Test
    void getSingleComment_WithValidId_ReturnsApiResponse() {
        long id = 1L;
        when(commentService.getSingleComment(id)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = commentController.getSinglePost(id);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(commentService).getSingleComment(id);
    }

    @Test
    void updateComment_WithValidDto_ReturnsApiResponse() {
        CommentDto commentDto = new CommentDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(commentService.updateComment(commentDto)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = commentController.updatePost(commentDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(commentService).updateComment(commentDto);
    }

    @Test
    void updateComment_WithInvalidDto_ReturnsErrorResponse() {
        CommentDto commentDto = new CommentDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setStatus_code(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatus_desc("Validation failed.");
        when(errorResponseUtil.createResponseEntity(bindingResult)).thenReturn(errorResponse);

        ResponseEntity<ApiResponse> response = commentController.updatePost(commentDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals("Validation failed.", response.getBody().getStatus_desc());
    }

    @Test
    void deleteComment_WithValidId_ReturnsApiResponse() {
        long id = 1L;
        when(commentService.deleteComment(id)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = commentController.deleteComment(id);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(commentService).deleteComment(id);
    }



}
