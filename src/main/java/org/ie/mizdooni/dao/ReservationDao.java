package org.ie.mizdooni.dao;

import org.ie.mizdooni.model.ReserveTableModel;

public class ReservationDao extends BaseDao<ReserveTableModel> {
    public ReservationDao() { super(ReserveTableModel.class); }
//    private int reservationNumber = -1;
//
//    static public void addObject(ReserveTableModel reserve) {
//        reserve.setId(getNewId());
//        allObjects.add(reserve);
//    }
//
//    public static List<ReserveTableModel> findTableReservedAtDate(String restaurantName, int tableNumber, Date date) {
//        var allResults = allObjects.stream()
//                .filter(
//                        model -> model.getRestaurantName().compareTo(restaurantName) == 0 &&
//                                model.getTableNumber() == tableNumber &&
//                                model.getDatetime().compareTo(date) == 0)
//                .collect(Collectors.toList());
//        return allResults;
//    }
//
//    public static List<ReserveTableModel> findByUsername(String username) {
//        var allResults = allObjects.stream()
//                .filter(
//                        model -> model.getUsername().compareTo(username) == 0)
//                .collect(Collectors.toList());
//        return allResults;
//    }
//
//    public static List<ReserveTableModel> findByUsernameAndRestaurant(String username, String restaurantName) {
//        return allObjects
//                .stream()
//                .filter(reservation -> reservation.getUsername().equals(username))
//                .filter(reservation -> reservation.getRestaurantName().equals(restaurantName))
//                .toList();
//    }
//
//    public static ReserveTableModel findById(int id) {
//        var results = allObjects.stream().filter(model -> model.getId() == id).collect(Collectors.toList());
//        if (results.isEmpty()) {
//            return null;
//        }
//        return results.get(0);
//    }
//
//    public static boolean deleteById(int id) {
//        return allObjects.removeIf(model -> model.getId() == id);
//    }
//
//    public static List<ReserveTableModel> findByRestauranName(String name) {
//        return allObjects.stream().filter(r -> r.getRestaurantName().equals(name)).toList();
//    }
//
//    public static List<ReserveTableModel> findForRestaurantInDate(String restaurantName, Date date) {
//        var allCasesInRestaurant = findByRestauranName(restaurantName);
//        var allInDate = allCasesInRestaurant.stream().filter(
//                r -> r.getDatetime().getDay() == date.getDay() &&
//                        r.getDatetime().getMonth() == date.getMonth() &&
//                        r.getDatetime().getYear() == date.getYear())
//                .toList();
//
//        return allInDate;
//    }
}
