package org.ie.mizdooni.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class MizdooniTime {
    public static String getMockedDate() {
        return mockedDate;
    }

    public static void setMockedDate(String mockedDate) {
        MizdooniTime.mockedDate = mockedDate;
    }

    private static String mockedDate="";

    public static LocalDateTime getCurrentDate(){
        if(mockedDate.isEmpty()){
            LocalDateTime now = LocalDateTime.now();
            return now;
        }
        else{
            LocalDateTime dateTime = LocalDateTime.parse(mockedDate);
            return dateTime;
        }
    }
}
