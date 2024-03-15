package app.karimax.creswave.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequest {
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;
}
