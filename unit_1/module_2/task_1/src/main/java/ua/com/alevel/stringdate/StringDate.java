package ua.com.alevel.stringdate;

import java.util.ArrayList;
import java.util.List;

public class StringDate {

    public static List<String> getStringDates(List<String> oldDates) {
        List<String> newDates = new ArrayList<>();

        for (String date : oldDates) {
            // 05/04/2020
            if (date.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$")) {
                String[] tmp = date.split("/");

                String newDate = tmp[2] + tmp[1] + tmp[0];
                newDates.add(newDate);
                // 04-05-2020
            } else if (date.matches("^(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])-[0-9]{4}$")) {
                String[] tmp = date.split("-");

                String newDate = tmp[2] + tmp[0] + tmp[1];
                newDates.add(newDate);
                // 2020/04/05
            } else if (date.matches("^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])$")) {
                String[] tmp = date.split("/");

                String newDate = tmp[0] + tmp[1] + tmp[2];
                newDates.add(newDate);
            }
        }
        return newDates;
    }
}
