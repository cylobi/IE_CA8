package org.ie.mizdooni.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.ie.mizdooni.utils.validator.ValidatorException;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReserveTableModel extends BaseModel {
    static private List<ReserveTableModel> allObjects = new ArrayList<>();
    static private int maxId = 100;

    static private int getNewId() {
        maxId += 1;
        return maxId;
    }

    public static void setMaxId(int maxId) {
        ReserveTableModel.maxId = maxId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Tehran")
    private Date datetime;

    @Override
    public void validate() throws ValidatorException {
        super.validate();
        if (datetime.getMinutes() != 0) {
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

    static public void addObject(ReserveTableModel reserve) {
        reserve.setId(getNewId());
        allObjects.add(reserve);
    }

    public static List<ReserveTableModel> findTableReservedAtDate(String restaurantName, int tableNumber, Date date) {
        var allResults = allObjects.stream()
                .filter(
                        model -> model.getRestaurantName().compareTo(restaurantName) == 0 &&
                                model.getTableNumber() == tableNumber &&
                                model.getDatetime().compareTo(date) == 0)
                .collect(Collectors.toList());
        return allResults;
    }

    public static List<ReserveTableModel> findByUsername(String username) {
        var allResults = allObjects.stream()
                .filter(
                        model -> model.getUsername().compareTo(username) == 0)
                .collect(Collectors.toList());
        return allResults;
    }

    public static List<ReserveTableModel> findByUsernameAndRestaurant(String username, String restaurantName) {
        return allObjects
                .stream()
                .filter(reservation -> reservation.getUsername().equals(username))
                .filter(reservation -> reservation.getRestaurantName().equals(restaurantName))
                .toList();
    }

    public static ReserveTableModel findById(int id) {
        var results = allObjects.stream().filter(model -> model.getId() == id).collect(Collectors.toList());
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    public static boolean deleteById(int id) {
        return allObjects.removeIf(model -> model.getId() == id);
    }

    public static List<ReserveTableModel> findByRestauranName(String name) {
        return allObjects.stream().filter(r -> r.getRestaurantName().equals(name)).toList();
    }

    public static List<ReserveTableModel> findForRestaurantInDate(String restaurantName, Date date) {
        var allCasesInRestaurant = findByRestauranName(restaurantName);
        var allInDate = allCasesInRestaurant.stream().filter(
                r -> r.getDatetime().getDay() == date.getDay() &&
                        r.getDatetime().getMonth() == date.getMonth() &&
                        r.getDatetime().getYear() == date.getYear())
                .toList();

        return allInDate;
    }
}
