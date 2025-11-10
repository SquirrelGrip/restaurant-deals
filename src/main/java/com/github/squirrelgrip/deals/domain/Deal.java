package com.github.squirrelgrip.deals.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;

public record Deal(Restaurant restaurant, RestaurantDeal restaurantDeal) {

    public String getRestaurantObjectId() {
        return restaurant.getObjectId();
    }

    public String getRestaurantName() {
        return restaurant.getName();
    }

    public String getRestaurantAddress1() {
        return restaurant.getAddress1();
    }

    public String getRestaurantSuburb() {
        return restaurant.getSuburb();
    }

    public LocalTime getRestaurantOpen() {
        return restaurant.getOpen();
    }

    public LocalTime getRestaurantClose() {
        return restaurant.getClose();
    }

    public String getDealObjectId() {
        return restaurantDeal.getObjectId();
    }

    public Integer getDiscount() {
        return restaurantDeal.getDiscount();
    }

    public Boolean getDineIn() {
        return restaurantDeal.getDineIn();
    }

    public Boolean getLightning() {
        return restaurantDeal.getLightning();
    }

    public Integer getQtyLeft() {
        return restaurantDeal.getQtyLeft();
    }

    public boolean isValidAt(LocalTime time) {
        return !time.isAfter(getDealEnd()) && !time.isBefore(getDealStart());
    }

    @JsonIgnore
    public LocalTime getDealStart() {
        return getLatest(restaurant.getOpen(), restaurantDeal.getOpen(), restaurantDeal.getStart());
    }

    @JsonIgnore
    public LocalTime getDealEnd() {
        return getEarliest(restaurant.getClose(), restaurantDeal.getClose(), restaurantDeal.getEnd());
    }

    private LocalTime getEarliest(LocalTime... time) {
        return Arrays.stream(time).min(Comparator.nullsLast(LocalTime::compareTo)).orElse(LocalTime.MAX);
    }

    public LocalTime getLatest(LocalTime... time) {
        return Arrays.stream(time).max(Comparator.nullsFirst(LocalTime::compareTo)).orElse(LocalTime.MIN);
    }

}
