package app.karimax.creswave.exception;


import app.karimax.creswave.config.Configs;
import app.karimax.creswave.dao.ApiResponse;
import lombok.AllArgsConstructor;

import java.util.List;
/**
 * Data Transfer Object attributes
 **/
@AllArgsConstructor
public class ErrorExceptionHandler {


	private Configs serviceConfig;
	String error_message_string;
	/**
	 * Handles errors for single string item
	 **/
	 public ApiResponse ErrorResponse() {
		 ApiResponse apiResponse=new ApiResponse();
		 apiResponse.setStatus_code(serviceConfig.getFailure_status_code());
		 apiResponse.setStatus_type(serviceConfig.getFailure_status_type());
		 apiResponse.setStatus_desc(error_message_string);


		 return apiResponse;
	 }
	/**
	 * Handles errors for arrays ie more than one error
	 **/
	 public ApiResponse ErrorResponseObject(List<String> error_strings) {
		 ApiResponse apiResponse=new ApiResponse();
		 apiResponse.setStatus_code(serviceConfig.getFailure_status_code());
		 apiResponse.setStatus_type(serviceConfig.getFailure_status_type());
		 apiResponse.setStatus_desc(error_message_string);
		 apiResponse.setResults_object(error_strings);
	    	return apiResponse;
	 }
	 

}
