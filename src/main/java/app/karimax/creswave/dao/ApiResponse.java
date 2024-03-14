package app.karimax.creswave.dao;
import com.google.gson.Gson;

import lombok.Data;
/**
 * Default Api  response  structure .
 */

@Data
public class ApiResponse {

    private int status_code;
    private int status_type;
    private String status_desc;
    private Object results_object;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
}
