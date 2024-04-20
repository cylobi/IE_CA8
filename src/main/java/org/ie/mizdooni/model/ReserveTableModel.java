package org.ie.mizdooni.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.ie.mizdooni.utils.validator.ValidatorException;

import java.util.Date;

public class ReserveTableModel extends BaseModel{
    private String username, restaurantName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    private int tableNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone="Asia/Tehran")
    private Date datetime;

    @Override
    public void validate() throws ValidatorException{
        super.validate();
        if(datetime.getMinutes() != 0){
            throw new ValidatorException("datetime should be a rounded time!");
        }
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationId) {
        this.reservationNumber = reservationId;
    }

    private int reservationNumber = -1;
}
