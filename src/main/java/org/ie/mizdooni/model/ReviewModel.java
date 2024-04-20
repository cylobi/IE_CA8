package org.ie.mizdooni.model;

import org.ie.mizdooni.utils.validator.ValidatorException;

import java.util.ArrayList;

public class ReviewModel extends BaseModel{
    String username, restaurantName;

    int foodRate, serviceRate, ambianceRate, overallRate;

    String comment;

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

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(int serviceRate) {
        this.serviceRate = serviceRate;
    }

    public int getAmbianceRate() {
        return ambianceRate;
    }

    public void setAmbianceRate(int ambianceRate) {
        this.ambianceRate = ambianceRate;
    }

    public int getOverallRate() {
        return overallRate;
    }

    public void setOverallRate(int overallRate) {
        this.overallRate = overallRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void validate()  throws ValidatorException{
        super.validate();
        var rateValues = new int [] {foodRate, serviceRate, ambianceRate, overallRate};
        for(var iter : rateValues){
            if(iter<0){
                throw new ValidatorException("Rate values must be positive or zero!");
            }
        }
    }
}
