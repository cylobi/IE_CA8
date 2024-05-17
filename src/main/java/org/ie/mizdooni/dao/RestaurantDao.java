package org.ie.mizdooni.dao;

import org.ie.mizdooni.model.RestaurantModel;

public class RestaurantDao extends BaseDao<RestaurantModel> {

    public RestaurantDao() {
        super(RestaurantModel.class);
    }

    // public static void addObject(RestaurantModel model) {
    // model.setId(getNewId());
    // allObjects.add(model);
    // }

    // public static List<RestaurantModel> getAllObjects() {
    // return allObjects;
    // }

    // static public RestaurantModel findByName(String name) {
    // var resultList = allObjects.stream().filter(model ->
    // model.getName().compareTo(name) == 0)
    // .collect(Collectors.toList());
    // if (resultList.isEmpty()) {
    // return null;
    // }

    // return resultList.get(0);
    // }

    // static public List<RestaurantModel> filterByName(String name) {
    // return getAllObjects().stream().filter(restaurant ->
    // restaurant.getName().contains(name)).toList();
    // }

    // static public List<RestaurantModel> filterByType(String type) {
    // return getAllObjects().stream().filter(restaurant ->
    // restaurant.getType().equals(type)).toList();
    // }

    // static public List<RestaurantModel> filterByCity(String city) {
    // return getAllObjects().stream().filter(restaurant ->
    // restaurant.getCity().equals(city)).toList();
    // }

    // static public List<RestaurantModel> sortByReview() {
    // List<RestaurantModel> allRestaurants = getAllObjects();
    // allRestaurants.sort(Comparator.comparingDouble(
    // (RestaurantModel restaurant) ->
    // ReviewModel.getOverallScoreByRestaurantName(restaurant.getName()))
    // .reversed());
    // return allRestaurants;
    // }

    // static public List<RestaurantModel> findByManager(String username) {
    // return allObjects.stream().filter(model ->
    // model.getManagerUsername().compareTo(username) == 0)
    // .collect(Collectors.toList());
    // }

    // static public RestaurantModel findById(int id) {
    // var result = allObjects.stream().filter(model -> model.getId() ==
    // id).collect(Collectors.toList());
    // if (result.isEmpty()) {
    // return null;
    // }

    // assert result.size() == 1;
    // return result.get(0);
    // }
}
