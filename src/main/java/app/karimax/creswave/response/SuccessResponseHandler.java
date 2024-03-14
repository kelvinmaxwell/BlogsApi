package app.karimax.creswave.response;


import app.karimax.creswave.config.Configs;
import app.karimax.creswave.dao.ApiResponse;
import lombok.AllArgsConstructor;

/**
 * success response for duplicate entries
 **/
@AllArgsConstructor
public class SuccessResponseHandler {
	private Configs serviceConfig;
    Object object;
	
	 public ApiResponse SuccResponse() {
		 ApiResponse apiResponseDTO=new ApiResponse();
		 apiResponseDTO.setStatus_code(serviceConfig.getSuccess_status_code());
		 apiResponseDTO.setStatus_type(serviceConfig.getSuccess_status_code());
		 apiResponseDTO.setStatus_desc(serviceConfig.getSuccess_status_desc());
		 apiResponseDTO.setResults_object(object);

		 return apiResponseDTO;
	 }
	 

}
