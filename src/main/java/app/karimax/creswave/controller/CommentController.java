package app.karimax.creswave.controller;

import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.CommentDto;
import app.karimax.creswave.exception.CustomBindingResultErrorResponse;
import app.karimax.creswave.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;
    private final CustomBindingResultErrorResponse errorResponseUtil;


    @PostMapping
    public ResponseEntity<ApiResponse> createComment(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { //check any existing errors and calls errorResponseUtil to clean and format errors


            return ResponseEntity.ok(errorResponseUtil.createResponseEntity(bindingResult));

        }
        return ResponseEntity.ok(commentService.createComment(commentDto)); //succes response
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getComments(Pageable pageable) { //specifies class as pageable
        return ResponseEntity.ok(commentService.getComments(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getSinglePost(@PathVariable("id") Long id) {

        return ResponseEntity.ok(commentService.getSingleComment(id));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updatePost(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return ResponseEntity.ok(errorResponseUtil.createResponseEntity(bindingResult));

        }
        return ResponseEntity.ok(commentService.updateComment(commentDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("id") Long id) {


        return ResponseEntity.ok(commentService.deleteComment(id));
    }
}
