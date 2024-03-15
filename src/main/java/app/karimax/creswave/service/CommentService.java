package app.karimax.creswave.service;

import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.CommentDto;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    ApiResponse createComment(CommentDto commentDto);

    ApiResponse updateComment(CommentDto commentDto);

    ApiResponse getComments(Pageable pageable);

    ApiResponse getSingleComment(Long id);

    ApiResponse deleteComment(Long id);
}
