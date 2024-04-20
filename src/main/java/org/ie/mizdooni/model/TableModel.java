package org.ie.mizdooni.model;

import org.ie.mizdooni.utils.validator.ValidatorException;

public class TableModel extends BaseModel{
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

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    private int tableNumber;
    private String restaurantName;
    private String managerUsername;//managerUseranme
    private int seatsNumber;

    @Override
    public void validate() throws ValidatorException {
        super.validate();

        if(seatsNumber <= 0){
            throw new ValidatorException("Seat number must be a natural integer!");
        }
    }
}
