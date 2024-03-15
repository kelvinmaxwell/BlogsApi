package app.karimax.creswave.exception;


import app.karimax.creswave.config.Configs;
import app.karimax.creswave.dao.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object attributes
 **/
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorExceptionHandler {


    private final Configs serviceConfig;
    String error_message_string;

    /**
     * Handles errors for single string item
     **/
    public ApiResponse errorResponse() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus_code(serviceConfig.getFailure_status_code());
        apiResponse.setStatus_type(serviceConfig.getFailure_status_type());
        apiResponse.setStatus_desc(error_message_string);


        return apiResponse;
    }

    public ApiResponse badRequestResponse() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus_code(serviceConfig.getBad_request_error_code());
        apiResponse.setStatus_type(serviceConfig.getFailure_status_code());
        apiResponse.setStatus_desc(error_message_string);


        return apiResponse;
    }

    public ApiResponse duplicateRequestResponse() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus_code(serviceConfig.getDuplicate_status_code());
        apiResponse.setStatus_type(serviceConfig.getFailure_status_type());
        apiResponse.setStatus_desc(error_message_string);


        return apiResponse;
    }

    public ApiResponse resourceNotFoundResponse() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus_code(serviceConfig.getNot_found_error_code());
        apiResponse.setStatus_type(serviceConfig.getFailure_status_type());
        apiResponse.setStatus_desc(serviceConfig.getNot_found_status_desc());


        return apiResponse;
    }

    /**
     * Handles errors for arrays ie more than one error
     **/
    public ApiResponse errorResponseObject(List<String> error_strings) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus_code(serviceConfig.getFailure_status_code());
        apiResponse.setStatus_type(serviceConfig.getFailure_status_type());
        apiResponse.setStatus_desc(error_message_string);
        apiResponse.setResults_object(error_strings);
        return apiResponse;
    }


}
