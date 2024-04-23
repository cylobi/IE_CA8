package org.ie.mizdooni.model;

import org.ie.mizdooni.utils.validator.ValidatorException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RestaurantModel extends BaseModel{
    static private List<RestaurantModel> allObjects = new ArrayList<>();
    static private int maxGeneratedId = 1000;
    static private int getNewId(){maxGeneratedId += 1; return maxGeneratedId;}

    String name;
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }
    public int getStartHour(){
        return Integer.valueOf(getStartTime().split(":")[0]);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getEndHour(){
        return Integer.valueOf(getEndTime().split(":")[0]);
    }

    public String getDescription() {
        return description;
    }

    public String getCity(){return address.city;}

    public void setDescription(String description) {
        this.description = description;
    }

    public RestaurantAddress getAddress() {
        return address;
    }

    public void setAddress(RestaurantAddress address) {
        this.address = address;
    }

    String managerUsername;
    String type;

    String startTime, endTime;
    Date startTimeObject;
    Date endTimeObject;
    String description;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    String imageUrl;

    RestaurantAddress address;

    private boolean checkTime(String time){
        String regex = "^([01]?[0-9]|2[0-3]):00$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    private void checkTimesAreSimple() throws ValidatorException{
        if(checkTime(startTime) == false || checkTime(endTime) == false){
            throw new ValidatorException("Start and end times should be valid and simple. like 00:00, 01:00, ... , 23:00");
        }
    }
    @Override
    public void validate() throws ValidatorException {
        super.validate();
        checkTimesAreSimple();
    }

    public static void addObject(RestaurantModel model){
        model.setId( getNewId() );
        allObjects.add(model);
    }

    public static List<RestaurantModel> getAllObjects(){
        return allObjects;
    }

    static public RestaurantModel findByName(String name){
        var resultList = allObjects.stream().filter(
                model -> model.getName().compareTo(name) == 0
        ).collect(Collectors.toList());
        if(resultList.isEmpty()){
            return null;
        }

        return resultList.get(0);
    }

    static public List<RestaurantModel> filterByName(String name)
    {
        return  getAllObjects()
                .stream()
                .filter(restaurant -> restaurant.getName().contains(name))
                .toList();
    }

    static public List<RestaurantModel> filterByType(String type)
    {
        return  getAllObjects()
                .stream()
                .filter(restaurant -> restaurant.getType().equals(type))
                .toList();
    }

    static public List<RestaurantModel> filterByCity(String city)
    {
        return  getAllObjects()
                .stream()
                .filter(restaurant -> restaurant.getCity().equals(city))
                .toList();
    }

    static public List<RestaurantModel> sortByReview()
    {
        List<RestaurantModel> allRestaurants = getAllObjects();
        allRestaurants.sort(
                Comparator.comparingDouble(
                        (RestaurantModel restaurant) -> ReviewModel.getOverallScoreByRestaurantName(restaurant.getName()))
                        .reversed()
        );
        return allRestaurants;
    }

    static public List<RestaurantModel> findByManager(String username){
        return allObjects.stream().filter(
                model -> model.getManagerUsername().compareTo(username) == 0
        ).collect(Collectors.toList());
    }

    static public RestaurantModel findById(int id){
        var result = allObjects.stream().filter(
                model -> model.getId() == id
        ).collect(Collectors.toList());
        if(result.isEmpty()){
            return null;
        }

        assert result.size() == 1;
        return result.get(0);
    }
}
