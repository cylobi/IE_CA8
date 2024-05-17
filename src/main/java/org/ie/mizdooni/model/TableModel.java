package org.ie.mizdooni.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.ie.mizdooni.utils.validator.ValidatorException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Embeddable
//class TableId implements Serializable {
//    private String restaurantName;
//    private int tableNumber;
//
//    public String getRestaurantName() {
//        return restaurantName;
//    }
//
//    public void setRestaurantName(String restaurantName) {
//        this.restaurantName = restaurantName;
//    }
//
//    public Integer getTableNumber() {
//        return tableNumber;
//    }
//
//    public void setTableNumber(Integer tableNumber) {
//        this.tableNumber = tableNumber;
//    }
//}

@Entity
@Table(name = "Tables")
public class TableModel extends BaseModel {

    @Id
    @GeneratedValue
    private Integer tableNumber;
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

    @OneToMany(mappedBy = "tableNumber")
    private List<ReserveTableModel> reservations = new ArrayList<>();

    public TableModel(Integer tableNumber, String restaurantName, Integer seatsNumber) {
        this.tableNumber = tableNumber;
        this.restaurantName = restaurantName;
        this.seatsNumber = seatsNumber;
    }

    public TableModel() {
    }

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
