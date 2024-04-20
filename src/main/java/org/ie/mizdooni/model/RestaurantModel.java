package org.ie.mizdooni.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.ie.mizdooni.utils.validator.BaseValidator;
import org.ie.mizdooni.utils.validator.ValidatorException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestaurantModel extends BaseModel{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RestaurantAddress getAddress() {
        return address;
    }

    public void setAddress(RestaurantAddress address) {
        this.address = address;
    }

    String managerUsername;
    String type;

    String startTime, endTime;
    Date startTimeObject;
    Date endTimeObject;
    String description;

    public class RestaurantAddress{
        public String country;
        public String city;
        public String street;
    }
    RestaurantAddress address;

    private boolean checkTime(String time){
        String regex = "^([01]?[0-9]|2[0-3]):00$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    private void checkTimesAreSimple() throws ValidatorException{
        if(checkTime(startTime) == false || checkTime(endTime) == false){
            throw new ValidatorException("Start and end times should be valid and simple. like 00:00, 01:00, ... , 23:00");
        }
    }
    @Override
    public void validate() throws ValidatorException {
        super.validate();
        checkTimesAreSimple();
    }
}
