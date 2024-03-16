package app.karimax.creswave.serviceimpl;

import app.karimax.creswave.config.Configs;
import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.PasswordUpdateDto;
import app.karimax.creswave.dao.UserDto;
import app.karimax.creswave.exception.ErrorExceptionHandler;
import app.karimax.creswave.model.Role;
import app.karimax.creswave.model.User;
import app.karimax.creswave.repository.RoleRepository;
import app.karimax.creswave.repository.UserRepository;
import app.karimax.creswave.request.AuthenticationRequest;
import app.karimax.creswave.response.AuthenticationResponse;
import app.karimax.creswave.response.SuccessResponseHandler;
import app.karimax.creswave.service.AuthenticationService;
import app.karimax.creswave.service.UserService;
import app.karimax.creswave.utils.CurrentTime;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * Implementation of Authentication service interface
 **/
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    public static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final UserRepository userRepository;
    private final Configs configs;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    User user = new User();

    @Override
    public ApiResponse signUp(UserDto userDto) {
        //check weather user email  and username exists

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            return new ErrorExceptionHandler(configs, configs.getDuplicateEntryDesc() + "-Email").duplicateRequestResponse();

        } else if (userRepository.findByUsername(userDto.getUsername()) != null) {
            //return duplicate entry if user with given email is found
            return new ErrorExceptionHandler(configs, configs.getDuplicateEntryDesc() + "-Username").duplicateRequestResponse();
        } else {
            try {
                //create new user object from user dto

                user.setUsername(userDto.getUsername());
                user.setEmail(userDto.getEmail());
                user.setStatus(userDto.getStatus());
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByName(userDto.getRole()));
                user.setRoles(roles);
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                user.setCreated_at(CurrentTime.getTime());

                userRepository.save(user);

                return new SuccessResponseHandler(configs, userDto).SuccResponse();
            } catch (Exception e) {
                return new ErrorExceptionHandler(configs, configs.getDefault_error_message()).errorResponse();
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
            logger.error("auth error" + e);
            return new ErrorExceptionHandler(configs, configs.getFailed_auth_desc()).errorResponse();

        }

    }

    @Override
    public ApiResponse updateProfile(UserDto userDto) {
        if (userRepository.findById(userDto.getId()).isPresent()) {
            user = userRepository.findById(userDto.getId()).get();
            if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) { //validate user password before makig change
                if (userRepository.findByUsername(userDto.getUsername()) == null) { //confirm new username does not exist
                    user.setUsername(user.getUsername());
                    Set<Role> roles = new HashSet<>();
                    roles.add(roleRepository.findByName(userDto.getRole())); //assums a user can have a single role
                    user.setRoles(roles);
                    user.setStatus(userDto.getStatus());
                    userRepository.save(user);
                    userDto.setPassword(""); //hide password from response
                    userDto = user.toDto(user);
                    return new SuccessResponseHandler(configs, userDto).SuccResponse();
                } else {
                    return new ErrorExceptionHandler(configs, configs.getDuplicateEntryDesc()).errorResponse(); //return duplicate entry error
                }


            } else {
                return new ErrorExceptionHandler(configs, configs.getFailed_auth_desc()).errorResponse(); //return  failed authentication error

            }


        }
        return new ErrorExceptionHandler(configs).resourceNotFoundResponse();
    }

    @Override
    public ApiResponse updatePassword(PasswordUpdateDto passwordUpdateDto) {
        if (userRepository.findById(passwordUpdateDto.getId()).isPresent()) {
            user = userRepository.findById(passwordUpdateDto.getId()).get();
            if (passwordEncoder.matches(passwordUpdateDto.getOld_password(), user.getPassword())) {

                user.setPassword(passwordEncoder.encode(passwordUpdateDto.getNew_password()));
                userRepository.save(user);
                return new SuccessResponseHandler(configs, passwordUpdateDto).SuccResponse();
            } else {
                return new ErrorExceptionHandler(configs, configs.getFailed_auth_desc()).errorResponse(); //return  failed authentication error

            }
        }
        return new ErrorExceptionHandler(configs).resourceNotFoundResponse();
    }

    @Override
    public ApiResponse deleteProfile(Long id) {
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
            user.setStatus(configs.getRecord_archived());//archive  user profile
            userRepository.save(user);
            return new SuccessResponseHandler(configs, null).SuccResponse();
        }
        return new ErrorExceptionHandler(configs).resourceNotFoundResponse();
    }
}
