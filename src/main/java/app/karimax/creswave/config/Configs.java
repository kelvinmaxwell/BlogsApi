package app.karimax.creswave.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class for application properties.
 * This class is annotated with Spring annotations for configuration and properties binding.
 */
@Configuration
@Data
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "configs")
public class Configs {

    private Integer missing_params_status_code;
    private Integer failure_result_code;
    private Integer delete_success_status_code;
    private Integer failure_status_code;
    private Integer success_status_code;
    private Integer failure_status_type;
    private Integer record_active;
    private Integer record_inactive;
    private Integer record_archived;
    private String date_format;
    private String default_error_message;
    private String success_status_desc;
    private String failure_status_desc;
    private String not_found_status_desc;
    private String session_expired;
    private String schema;
    private String duplicateEntryDesc;
    private String permission_denied_status_desc;
    private String user_registration_message;
    private String app_name;
    private String jwt_secret;
    private String jwt_expiration;
    private String failed_auth_desc;

}
