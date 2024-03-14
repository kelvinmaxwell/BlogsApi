package app.karimax.creswave.exception;


import app.karimax.creswave.config.Configs;
import app.karimax.creswave.dao.ApiResponse;
import lombok.AllArgsConstructor;

/**
 * Error response for duplicate entries
 **/
@AllArgsConstructor
public class DuplicateEntryException {

private Configs serviceConfig;
	 public ApiResponse DuplicateErrorResponse() {
		 ApiResponse apiResponse=new ApiResponse();
		 apiResponse.setStatus_code(serviceConfig.getFailure_status_code());
		 apiResponse.setStatus_code(serviceConfig.getFailure_status_type());
		 apiResponse.setStatus_desc(serviceConfig.getDuplicateEntryDesc());

		 return apiResponse;
	 }
}
