package app.karimax.creswave.controller;

import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.UserDto;
import app.karimax.creswave.exception.CustomBindingResultErrorResponse;
import app.karimax.creswave.request.AuthenticationRequest;
import app.karimax.creswave.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final CustomBindingResultErrorResponse errorResponseUtil;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return ResponseEntity.ok(errorResponseUtil.createResponseEntity(bindingResult));

        }
        return ResponseEntity.ok(authenticationService.signUp(userDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signIn(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(errorResponseUtil.createResponseEntity(bindingResult));

        }
        return ResponseEntity.ok(authenticationService.signIn(authenticationRequest));
    }


}
