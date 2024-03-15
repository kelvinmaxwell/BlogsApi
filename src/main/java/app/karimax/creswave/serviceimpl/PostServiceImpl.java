package app.karimax.creswave.serviceimpl;

import app.karimax.creswave.config.Configs;
import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.PostDto;
import app.karimax.creswave.exception.ErrorExceptionHandler;
import app.karimax.creswave.model.Post;
import app.karimax.creswave.repository.PostRepository;
import app.karimax.creswave.repository.UserRepository;
import app.karimax.creswave.response.SuccessResponseHandler;
import app.karimax.creswave.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final Configs configs;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private Post post = new Post();

    @Override
    public ApiResponse createPost(PostDto postDto) {

        post = post.fromDto(postDto, userRepository.findByUsername(postDto.getUsername()));//map Dto to post object
        post = postRepository.save(post);
        postDto = post.toDto();//  map post to Dto

        return new SuccessResponseHandler(configs, postDto).SuccResponse(); //call success response class when response is as expected
    }

    @Override
    public ApiResponse updatePost(PostDto postDto) {
        post = post.fromDto(postDto, userRepository.findByUsername(postDto.getUsername()));
        post = postRepository.save(post); //save object
        postDto = post.toDto(); //map returned object for response

        return new SuccessResponseHandler(configs, postDto).SuccResponse(); //call success response class when response is as expected
    }

    @Override
    public ApiResponse getPosts(Pageable pageable) { //paginate set to default page as 0 and 10 items on page
        Page<Post> posts = postRepository.findAll(pageable);

        return new SuccessResponseHandler(configs, posts.stream()   //map posts array to postsDto array for response
                .map(Post::toDto)
                .collect(Collectors.toList())).SuccResponse(); //call success response class when response is as expected

    }

    @Override
    public ApiResponse getSinglePost(Long id) {
        if (postRepository.findById(id).isPresent()) {
            PostDto postDto = postRepository.findById(id).get().toDto();
            return new SuccessResponseHandler(configs, postDto).SuccResponse(); //call success response class when response is as expected
        }
        return new ErrorExceptionHandler(configs).resourceNotFoundResponse(); //error  if resource is not found
    }

    @Override
    public ApiResponse deletePost(Long id) {

        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
            return new SuccessResponseHandler(configs, null).SuccResponse(); //return null response object for object delete opreation
        }
        return new ErrorExceptionHandler(configs).resourceNotFoundResponse(); //error if resource is not found
    }
}
