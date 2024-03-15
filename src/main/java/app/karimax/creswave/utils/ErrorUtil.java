package app.karimax.creswave.utils;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;

@RequiredArgsConstructor
public class ErrorUtil {
    private final ArrayList<String> error_array_list;

    public String removeDuplicates() {
        HashSet<String> uniqueSet = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();
        String err_string = "";

        for (String item : error_array_list) {
            if (uniqueSet.add(item)) {
                // If the item is successfully added to the HashSet, it's unique.
                result.add(item);
            }
        }
//combine errors to a single  string for display
        for (int k = 0; k < result.size(); k++) {
            if (k == result.size() - 1) {
                err_string += result.get(k); //for the last item do not add ,
            } else {
                err_string += result.get(k) + ","; //add , for subsequent items(errors)
            }
        }

        return err_string; //return formatted string
    }
}
