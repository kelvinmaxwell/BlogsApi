package app.karimax.creswave.service;

import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.PasswordUpdateDto;
import app.karimax.creswave.dao.UserDto;
import app.karimax.creswave.request.AuthenticationRequest;

 public interface AuthenticationService {
     ApiResponse signUp(UserDto userDto);

    ApiResponse signIn(AuthenticationRequest authenticationRequest);

    ApiResponse updateProfile(UserDto userDto);

     ApiResponse updatePassword(PasswordUpdateDto passwordUpdateDto);

    ApiResponse deleteProfile(Long id);
}
