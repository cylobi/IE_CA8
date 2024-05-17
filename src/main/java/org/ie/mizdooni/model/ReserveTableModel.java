package org.ie.mizdooni.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import org.ie.mizdooni.utils.validator.ValidatorException;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Reservations")
public class ReserveTableModel extends BaseModel {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "CLIENT_USERNAME", referencedColumnName = "username")
    private ClientUserModel clientUsername;
    @ManyToOne
    @JoinColumn(name = "RESTAURANT_NAME", referencedColumnName = "name")
    private RestaurantModel restaurantName;
    @ManyToOne
    @JoinColumn(name = "TABLE_NUMBER", referencedColumnName = "tableNumber")
    private TableModel tableNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Tehran")
    private Date reservationDateTime;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ClientUserModel getUsername() {
        return clientUsername;
    }
    public void setUsername(ClientUserModel username) {
        this.clientUsername = username;
    }
    public RestaurantModel getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(RestaurantModel restaurantName) {
        this.restaurantName = restaurantName;
    }
    public TableModel getTableNumber() {
        return tableNumber;
    }
    public void setTableNumber(TableModel tableNumber) {
        this.tableNumber = tableNumber;
    }
    public Date getDatetime() {
        return reservationDateTime;
    }
    public void setDatetime(Date reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }


    @Override
    public void validate() throws ValidatorException {
        super.validate();
        if (reservationDateTime.getMinutes() != 0) {
            throw new ValidatorException("datetime should be a rounded time!");
        }
    }

}
