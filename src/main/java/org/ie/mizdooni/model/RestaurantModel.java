package org.ie.mizdooni.model;

import org.ie.mizdooni.utils.validator.ValidatorException;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "Restaurants")
public class RestaurantModel extends BaseModel {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(unique = true)
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String managerUsername;

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getStartHour() {
        return Integer.valueOf(getStartTime().split(":")[0]);
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

    public int getEndHour() {
        return Integer.valueOf(getEndTime().split(":")[0]);
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return address.city;
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

    String startTime, endTime;

    @Transient
    LocalTime startTimeObject;
    @Transient
    LocalTime endTimeObject; // SOS

    @Column(columnDefinition = "TEXT")
    String description;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    String imageUrl;

    @Embedded
    RestaurantAddress address;

    @OneToMany(mappedBy = "restaurantName")
    private List<ReserveTableModel> reservations = new ArrayList<>();

    private boolean checkTime(String time) {
        String regex = "^([01]?[0-9]|2[0-3]):00$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    private void checkTimesAreSimple() throws ValidatorException {
        if (checkTime(startTime) == false || checkTime(endTime) == false) {
            throw new ValidatorException(
                    "Start and end times should be valid and simple. like 00:00, 01:00, ... , 23:00");
        }
    }

    @Override
    public void validate() throws ValidatorException {
        super.validate();
        checkTimesAreSimple();
    }

    @OneToMany(mappedBy = "restaurant")
    private List<TableModel> tables;

}
