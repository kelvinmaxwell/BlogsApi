package app.karimax.creswave.dao;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PasswordUpdateDto {
    @NotNull(message = "id is required")
    Long id;
    @NotNull(message = "old password is required")
    String old_password;
    @NotNull(message = "new  password is required")
    String new_password;
}
