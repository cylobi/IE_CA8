package org.ie.mizdooni.model;

import org.ie.mizdooni.utils.validator.ValidatorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableModel extends BaseModel{
    private static List<TableModel> allObjects = new ArrayList<>();

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

    public static List<TableModel> findByRestaurantName(String restaurantName){
        return allObjects.stream()
                .filter(model -> model.getRestaurantName().compareTo(restaurantName) == 0)
                .collect(Collectors.toList());
    }

    public static void addObject(TableModel table){
        allObjects.add(table);
    }

    public static TableModel findByRestaurantNameAndNumber(String restaurantName, int tableNumber){
        var allResults = allObjects.stream()
                .filter(
                        model -> model.getRestaurantName().compareTo(restaurantName) == 0 &&
                        model.getTableNumber() == tableNumber
                ).collect(Collectors.toList());
        if(allResults.isEmpty()){
            return null;
        }
        assert allResults.size() == 1;
        return allResults.get(0);
    }

}
