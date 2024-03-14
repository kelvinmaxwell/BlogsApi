package app.karimax.creswave.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CurrentTime {
    //methods returns current time
    public  static Timestamp getTime(){
        LocalDateTime currentDate = LocalDateTime.now();
        return Timestamp.valueOf(currentDate);
    }
}
