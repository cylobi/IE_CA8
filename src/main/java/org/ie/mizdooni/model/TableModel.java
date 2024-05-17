package org.ie.mizdooni.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.ie.mizdooni.utils.validator.ValidatorException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

class TableId implements Serializable {
    private String restaurantName;
    private Integer tableNumber;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }
}

@Entity
@Table(name = "Tables")
@IdClass(TableId.class)
public class TableModel extends BaseModel {
    @Id
    @Column(name = "tableNumber", nullable = false)
    private int tableNumber;

    @Id
    @Column(name = "restaurantName", nullable = false)
    private String restaurantName;

    @ManyToOne(fetch = FetchType.LAZY) // many times just we want to read restaurants with its tables. not reversed.
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurantName", referencedColumnName = "name", insertable = false, updatable = false)
    private RestaurantModel restaurant;

    @Column(name = "seatsNumber")
    private Integer seatsNumber;

    @Column(name = "managerUsername")
    private String managerUsername;

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    @Override
    public void validate() throws ValidatorException {
        super.validate();

        if (seatsNumber <= 0) {
            throw new ValidatorException("Seat number must be a natural integer!");
        }
    }

}
