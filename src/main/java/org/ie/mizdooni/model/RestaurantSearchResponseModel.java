package org.ie.mizdooni.model;

import java.util.ArrayList;

public class RestaurantSearchResponseModel extends BaseModel{
    public ArrayList<RestaurantModel> restaurants;

    public RestaurantSearchResponseModel(ArrayList<RestaurantModel> restaurants) {
        this.restaurants = restaurants;
    }
}
