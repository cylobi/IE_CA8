package org.ie.mizdooni.model;

import org.ie.mizdooni.utils.MizdooniTime;
import org.ie.mizdooni.utils.validator.ValidatorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewModel extends BaseModel{
    static private List<ReviewModel> allObjects = new ArrayList<>();

    String username, restaurantName;

    float foodRate, serviceRate, ambianceRate, overallRate;

    String comment;

    public String getUsername() {
        return username;
    }

    public static List<ReviewModel> getAllObjects() {return allObjects;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public float getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(float foodRate) {
        this.foodRate = foodRate;
    }

    public float getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(float serviceRate) {
        this.serviceRate = serviceRate;
    }

    public float getAmbianceRate() {
        return ambianceRate;
    }

    public void setAmbianceRate(float ambianceRate) {
        this.ambianceRate = ambianceRate;
    }

    public float getOverallRate() {
        return overallRate;
    }

    public void setOverallRate(float overallRate) {
        this.overallRate = overallRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    private LocalDateTime datetime;

    @Override
    public void validate()  throws ValidatorException{
        super.validate();
        var rateValues = new float [] {foodRate, serviceRate, ambianceRate, overallRate};
        for(var iter : rateValues){
            if(iter<0){
                throw new ValidatorException("Rate values must be positive or zero!");
            }
        }
    }

    public static void addObject(ReviewModel model){
        model.setDatetime(MizdooniTime.getCurrentDate());
        allObjects.add(model);
    }

    public static Boolean isReviewExist(String username, String restaurantName)
    {
        return getAllObjects()
                .stream()
                .anyMatch(review -> (review.getUsername() == username) && (review.getRestaurantName() == restaurantName));
    }

    public static void deleteReview(ReviewModel reviewModel) throws ValidatorException
    {
        var selectedReview = getReviewByUserAndRestaurant(reviewModel.getUsername(), reviewModel.getRestaurantName());
        allObjects.remove(selectedReview);
    }

    public static ReviewModel getReviewByUserAndRestaurant(String username, String restaurantName)
    {
        var rev = getAllObjects()
                .stream()
                .filter(review -> review.getUsername().equals(username))
                .filter(review -> review.getRestaurantName().equals(restaurantName))
                .findFirst();
        if (rev.isPresent())
        {
            return rev.get();
        }
        else return null;
    }

    public static List<ReviewModel> findByRestaurantName(String restaurantName){
        List<ReviewModel> result = allObjects.stream().filter(
            review -> review.getRestaurantName().compareTo(restaurantName) == 0
        ).collect(Collectors.toList());
        return result;
    }

    public static float getOverallScoreByRestaurantName(String restaurantName)
    {
        return calculateAverageForRestaurant(restaurantName).getOverallRate();
    }



    public static ReviewModel calculateAverageForRestaurant(String restaurantName){
        float foodRate=0, serviceRate=0, ambianceRate=0, overallRate=0;
        var averageModel = new ReviewModel();
        var reviews = findByRestaurantName(restaurantName);
        for(var iter : reviews){
            foodRate += iter.getFoodRate();
            serviceRate += iter.getServiceRate();
            ambianceRate += iter.getAmbianceRate();
            overallRate += iter.getOverallRate();
        }
        int len = reviews.size();
        if(len == 0){
            return averageModel;
        }

        foodRate /= len;
        serviceRate /= len;
        ambianceRate /= len;
        overallRate /= len;

        averageModel.setFoodRate(foodRate);
        averageModel.setServiceRate(serviceRate);
        averageModel.setAmbianceRate(ambianceRate);
        averageModel.setOverallRate(overallRate);

        return averageModel;
    }

}
