package app.karimax.creswave;


import app.karimax.creswave.controller.AuthenticationController;
import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.dao.PasswordUpdateDto;
import app.karimax.creswave.dao.UserDto;
import app.karimax.creswave.exception.CustomBindingResultErrorResponse;
import app.karimax.creswave.request.AuthenticationRequest;
import app.karimax.creswave.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private CustomBindingResultErrorResponse errorResponseUtil;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signUp_WithValidDto_ReturnsApiResponse() {
        UserDto userDto = new UserDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(authenticationService.signUp(userDto)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = authenticationController.signUp(userDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(authenticationService).signUp(userDto);
    }

    @Test
    void signUp_WithInvalidDto_ReturnsErrorResponse() {
        UserDto userDto = new UserDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ApiResponse errorResponse = new ApiResponse();
        when(errorResponseUtil.createResponseEntity(bindingResult)).thenReturn(errorResponse);

        ResponseEntity<ApiResponse> response = authenticationController.signUp(userDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals(errorResponse, response.getBody());
    }

    @Test
    void signIn_WithValidRequest_ReturnsApiResponse() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(authenticationService.signIn(authenticationRequest)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = authenticationController.signIn(authenticationRequest, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(authenticationService).signIn(authenticationRequest);
    }

    @Test
    void signIn_WithInvalidRequest_ReturnsErrorResponse() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ApiResponse errorResponse = new ApiResponse();
        when(errorResponseUtil.createResponseEntity(bindingResult)).thenReturn(errorResponse);

        ResponseEntity<ApiResponse> response = authenticationController.signIn(authenticationRequest, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals(errorResponse, response.getBody());
    }

    @Test
    void updateProfile_WithValidDto_ReturnsApiResponse() {
        UserDto userDto = new UserDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(authenticationService.updateProfile(userDto)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = authenticationController.updateProfile(userDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(authenticationService).updateProfile(userDto);
    }

    @Test
    void updateProfile_WithInvalidDto_ReturnsErrorResponse() {
        UserDto userDto = new UserDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ApiResponse errorResponse = new ApiResponse();
        when(errorResponseUtil.createResponseEntity(bindingResult)).thenReturn(errorResponse);

        ResponseEntity<ApiResponse> response = authenticationController.updateProfile(userDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals(errorResponse, response.getBody());
    }

    @Test
    void updatePassword_WithValidDto_ReturnsApiResponse() {
        PasswordUpdateDto passwordUpdateDto = new PasswordUpdateDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(authenticationService.updatePassword(passwordUpdateDto)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = authenticationController.updatePassword(passwordUpdateDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(authenticationService).updatePassword(passwordUpdateDto);
    }

    @Test
    void updatePassword_WithInvalidDto_ReturnsErrorResponse() {
        PasswordUpdateDto passwordUpdateDto = new PasswordUpdateDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ApiResponse errorResponse = new ApiResponse();
        when(errorResponseUtil.createResponseEntity(bindingResult)).thenReturn(errorResponse);

        ResponseEntity<ApiResponse> response = authenticationController.updatePassword(passwordUpdateDto, bindingResult);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals(errorResponse, response.getBody());
    }

    @Test
    void deleteProfile_WithValidId_ReturnsApiResponse() {
        long id = 1L;
        when(authenticationService.deleteProfile(id)).thenReturn(new ApiResponse());

        ResponseEntity<ApiResponse> response = authenticationController.deleteProfile(id);

        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        verify(authenticationService).deleteProfile(id);
    }
}
