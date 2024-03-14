package app.karimax.creswave.serviceimpl;

import app.karimax.creswave.config.Configs;
import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.UserDto;
import app.karimax.creswave.exception.ErrorExceptionHandler;
import app.karimax.creswave.model.User;
import app.karimax.creswave.repository.UserRepository;
import app.karimax.creswave.request.AuthenticationRequest;
import app.karimax.creswave.response.AuthenticationResponse;
import app.karimax.creswave.response.SuccessResponseHandler;
import app.karimax.creswave.service.AuthenticationService;
import app.karimax.creswave.service.UserService;
import app.karimax.creswave.utils.CurrentTime;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



/**
 * Implementation of Authentication service interface
 **/
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final Configs configs;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public ApiResponse signUp(UserDto userDto) {
        //check weather user email  and username exists

        if (userRepository.findByEmail(userDto.getEmail()) != null ) {
            return new ErrorExceptionHandler(configs, configs.getDuplicateEntryDesc() +"-Email").ErrorResponse();

        } else if(userRepository.findByUsername(userDto.getUsername())!=null){
            //return duplicate entry if user with given email is found
            return new ErrorExceptionHandler(configs, configs.getDuplicateEntryDesc() +"-Username").ErrorResponse();
        }
        else {
            try {
                //create new user object from user dto
                User user = new User();
                user.setUsername(userDto.getUsername());
                user.setEmail(userDto.getEmail());
                user.setStatus(userDto.getStatus());
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                user.setCreated_at(CurrentTime.getTime());

                userRepository.save(user);

                return new SuccessResponseHandler(configs, userDto).SuccResponse();
            } catch (Exception e) {
                return new ErrorExceptionHandler(configs, configs.getDefault_error_message()).ErrorResponse();
            }
        }
    }

    @Override
    public ApiResponse signIn(AuthenticationRequest authenticationRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            // Authentication was successful
            User user = userRepository.findByUsername(authenticationRequest.getUsername());
            //generate user token
            String jwt = userService.generateToken(user);
            //build token object

            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setToken(jwt);
            //return token

            return new SuccessResponseHandler(configs, authenticationResponse).SuccResponse();

        } catch (AuthenticationException e) {
            // Authentication failed
            logger.error("auth error"+e);
            return new ErrorExceptionHandler(configs, configs.getFailed_auth_desc()).ErrorResponse();

        }

    }
}
