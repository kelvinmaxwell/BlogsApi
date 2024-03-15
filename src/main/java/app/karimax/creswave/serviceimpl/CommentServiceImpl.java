package app.karimax.creswave.serviceimpl;

import app.karimax.creswave.config.Configs;
import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.CommentDto;
import app.karimax.creswave.exception.ErrorExceptionHandler;
import app.karimax.creswave.model.Comment;
import app.karimax.creswave.repository.CommentRepository;
import app.karimax.creswave.repository.PostRepository;
import app.karimax.creswave.repository.UserRepository;
import app.karimax.creswave.response.SuccessResponseHandler;
import app.karimax.creswave.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final Configs configs;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private Comment comment = new Comment();
    @Override
    public ApiResponse createComment(CommentDto commentDto) {
        comment = comment.fromDto(commentDto, userRepository.findByUsername(commentDto.getUsername()),postRepository.findById(commentDto.getPost_id()).get());//map Dto to comment object
        comment = commentRepository.save(comment); //save object
        commentDto = comment.toDto();//  map post to Dto for response

        return new SuccessResponseHandler(configs, commentDto).SuccResponse(); //call success response class when response is as expected
    }

    @Override
    public ApiResponse updateComment(CommentDto commentDto) {
        comment = comment.fromDto(commentDto, userRepository.findByUsername(commentDto.getUsername()),postRepository.findById(commentDto.getPost_id()).get());
        comment = commentRepository.save(comment);  //update object with given id
        commentDto = comment.toDto(); //map returned object for response

        return new SuccessResponseHandler(configs, commentDto).SuccResponse();//call success response class when response is as expected
    }

    @Override
    public ApiResponse getComments(Pageable pageable) { //paginate set to default page as 0 and 10 items on page
        Page<Comment> comments = commentRepository.findAll(pageable);

        return new SuccessResponseHandler(configs, comments.stream()     //map comments array to commentDto array for response
                .map(Comment::toDto)
                .collect(Collectors.toList())).SuccResponse(); //call success response class when response is as expected
    }

    @Override
    public ApiResponse getSingleComment(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            CommentDto commentDto = commentRepository.findById(id).get().toDto();
            return new SuccessResponseHandler(configs, commentDto).SuccResponse(); //call success response class when response is as expected
        }
        return new ErrorExceptionHandler(configs).resourceNotFoundResponse(); //error if resource is not found
    }

    @Override
    public ApiResponse deleteComment(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            commentRepository.deleteById(id);
            return new SuccessResponseHandler(configs, null).SuccResponse(); //return null response object for object delete opreation
        }
        return new ErrorExceptionHandler(configs).resourceNotFoundResponse(); //error if resource is not found
    }
}
