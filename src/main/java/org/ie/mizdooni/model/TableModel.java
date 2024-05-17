package org.ie.mizdooni.model;

import org.ie.mizdooni.utils.validator.ValidatorException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// @Embeddable
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
    // private static List<TableModel> allObjects = new ArrayList<>();

    @Id
    @Column(name = "tableNumber", nullable = false)
    private int tableNumber;

    @Id
    @Column(name = "restaurantName", nullable = false)
    private String restaurantName;

    @ManyToOne
    @JoinColumn(name = "restaurantName", referencedColumnName = "name", insertable = false, updatable = false)
    private RestaurantModel restaurant;

    @Column(name = "seatsNumber")
    private Integer seatsNumber;

    @Column(name = "managerUsername")
    private String managerUsername;

    @Override
    public void validate() throws ValidatorException {
        super.validate();

        if (seatsNumber <= 0) {
            throw new ValidatorException("Seat number must be a natural integer!");
        }
    }

}
