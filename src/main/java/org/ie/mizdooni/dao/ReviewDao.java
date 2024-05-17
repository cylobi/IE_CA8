package org.ie.mizdooni.dao;

import org.ie.mizdooni.model.ReviewModel;

public class ReviewDao extends BaseDao<ReviewModel> {

    public ReviewDao() {
        super(ReviewModel.class);
    }

    // public static void addObject(ReviewModel model){
    // model.setDatetime(MizdooniTime.getCurrentDate());
    // allObjects.add(model);
    // }

    // public static List<ReviewModel> getAllObject(){
    // return allObjects.stream().toList();
    // }
    // public static Boolean isReviewExist(String username, String restaurantName)
    // {
    // return getAllObjects()
    // .stream()
    // .anyMatch(review -> (review.getUsername() == username) &&
    // (review.getRestaurantName() == restaurantName));
    // }

    // public static void deleteReview(ReviewModel reviewModel) throws
    // ValidatorException
    // {
    // var selectedReview = getReviewByUserAndRestaurant(reviewModel.getUsername(),
    // reviewModel.getRestaurantName());
    // allObjects.remove(selectedReview);
    // }

    // public static ReviewModel getReviewByUserAndRestaurant(String username,
    // String restaurantName)
    // {
    // var rev = getAllObjects()
    // .stream()
    // .filter(review -> review.getUsername().equals(username))
    // .filter(review -> review.getRestaurantName().equals(restaurantName))
    // .findFirst();
    // if (rev.isPresent())
    // {
    // return rev.get();
    // }
    // else return null;
    // }

    // public static List<ReviewModel> findByRestaurantName(String restaurantName){
    // List<ReviewModel> result = allObjects.stream().filter(
    // review -> review.getRestaurantName().compareTo(restaurantName) == 0
    // ).collect(Collectors.toList());
    // return result;
    // }

    // public static float getOverallScoreByRestaurantName(String restaurantName)
    // {
    // return calculateAverageForRestaurant(restaurantName).getOverallRate();
    // }

    // public static ReviewModel calculateAverageForRestaurant(String
    // restaurantName){
    // float foodRate=0, serviceRate=0, ambianceRate=0, overallRate=0;
    // var averageModel = new ReviewModel();
    // var reviews = findByRestaurantName(restaurantName);
    // for(var iter : reviews){
    // foodRate += iter.getFoodRate();
    // serviceRate += iter.getServiceRate();
    // ambianceRate += iter.getAmbianceRate();
    // overallRate += iter.getOverallRate();
    // }
    // int len = reviews.size();
    // if(len == 0){
    // return averageModel;
    // }

    // foodRate /= len;
    // serviceRate /= len;
    // ambianceRate /= len;
    // overallRate /= len;

    // averageModel.setFoodRate(foodRate);
    // averageModel.setServiceRate(serviceRate);
    // averageModel.setAmbianceRate(ambianceRate);
    // averageModel.setOverallRate(overallRate);

    // return averageModel;
    // }

}
