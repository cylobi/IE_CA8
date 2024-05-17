package org.ie.mizdooni.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class RestaurantAddress {
    public String country;
    public String city;
    public String street;
}