package app.karimax.creswave.exception;

import app.karimax.creswave.config.Configs;
import app.karimax.creswave.dao.ApiResponse;
import app.karimax.creswave.utils.ErrorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomBindingResultErrorResponse {
    private final Configs configs;

    public ApiResponse createResponseEntity(BindingResult bindingResult) {
        List<ObjectError> object = bindingResult.getAllErrors();
        ArrayList<String> error_array_list = new ArrayList<>();
        for (int k = 0; k < object.size(); k++) {
            String[] parts = String.valueOf(object.get(k)).split(";", 0); // Split into at most 2 parts
            String result = parts[0];
            error_array_list.add(result);
        }
        String uniqueList = new ErrorUtil(error_array_list).removeDuplicates(); //remove duplicates from array


        return new ErrorExceptionHandler(configs, uniqueList).badRequestResponse(); //convert the error response object into a JSON string representation using the writeValueAsString method.

    }
}
