package app.karimax.creswave.dao;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object attributes
 **/
@Data
public class UserDto {
    Long id;
    @NotBlank(message = "Username is required")  //validates objects has met set criteria
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;
    @NotNull(message = "Status is required")
    private Integer status;

    @NotNull(message = "role is required")
    private String role;
}
