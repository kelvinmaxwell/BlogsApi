package app.karimax.creswave.controller;

import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.UserDto;
import app.karimax.creswave.request.AuthenticationRequest;

import app.karimax.creswave.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody UserDto userDto ) {

        return ResponseEntity.ok(authenticationService.signUp(userDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signIn(@RequestBody AuthenticationRequest authenticationRequest ) {
        return ResponseEntity.ok(authenticationService.signIn(authenticationRequest));
    }


}
