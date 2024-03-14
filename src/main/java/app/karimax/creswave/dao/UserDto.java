package app.karimax.creswave.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

/**
 * Data Transfer Object attributes
 **/
@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private Integer status;
}
