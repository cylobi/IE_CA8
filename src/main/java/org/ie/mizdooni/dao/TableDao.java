package org.ie.mizdooni.dao;

import jakarta.persistence.Table;
import org.ie.mizdooni.model.TableModel;

public class TableDao extends BaseDao<TableModel> {
    public TableDao() {
        super(TableModel.class);
    }

    private static class SingletonHelper {
        private static final TableDao INSTANCE = new TableDao();
    }

    public static TableDao getInstance() {
        return TableDao.SingletonHelper.INSTANCE;
    }

    // public static List<TableModel> findByRestaurantName(String restaurantName){
    // return allObjects.stream()
    // .filter(model -> model.getRestaurantName().compareTo(restaurantName) == 0)
    // .collect(Collectors.toList());
    // }

    // public static void addObject(TableModel table){
    // allObjects.add(table);
    // }

    // public static TableModel findByRestaurantNameAndNumber(String restaurantName,
    // int tableNumber){
    // var allResults = allObjects.stream()
    // .filter(
    // model -> model.getRestaurantName().compareTo(restaurantName) == 0 &&
    // model.getTableNumber() == tableNumber
    // ).collect(Collectors.toList());
    // if(allResults.isEmpty()){
    // return null;
    // }
    // assert allResults.size() == 1;
    // return allResults.get(0);
    // }
}
