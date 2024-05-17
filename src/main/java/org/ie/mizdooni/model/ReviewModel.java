package org.ie.mizdooni.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.ie.mizdooni.utils.MizdooniTime;
import org.ie.mizdooni.utils.validator.ValidatorException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Reviews")
public class ReviewModel extends BaseModel {
    // static private List<ReviewModel> allObjects = new ArrayList<>();
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "username", nullable = false)
    String username;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private ClientUserModel user;

    @Column(name = "restaurantName", nullable = false)
    String restaurantName;
    @ManyToOne(fetch = FetchType.LAZY) // many times just we want to read restaurants with its tables. not reversed.
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurantName", referencedColumnName = "name", insertable = false, updatable = false)
    private RestaurantModel restaurant;

    Float foodRate, serviceRate, ambianceRate, overallRate;

    @Column(columnDefinition = "TEXT")
    String comment;

    private LocalDateTime datetime;

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

    @JsonIgnore
    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }



    @Override
    public void validate() throws ValidatorException {
        super.validate();
        var rateValues = new float[] { foodRate, serviceRate, ambianceRate, overallRate };
        for (var iter : rateValues) {
            if (iter < 0) {
                throw new ValidatorException("Rate values must be positive or zero!");
            }
        }
    }

}
