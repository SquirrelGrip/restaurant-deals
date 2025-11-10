package com.github.squirrelgrip.deals.domain;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;

public class Deal {
    private final Restaurant restaurant;
    private final RestaurantDeal restaurantDeal;

    public Deal(
            Restaurant restaurant,
            RestaurantDeal restaurantDeal
    ) {
        this.restaurant = restaurant;
        this.restaurantDeal = restaurantDeal;
    }

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

    public boolean isValidAt(LocalTime time) {
        return !time.isAfter(getDealEnd()) && !time.isBefore(getDealStart());
    }

    public LocalTime getDealStart() {
        return getLatest(restaurant.getOpen(), restaurantDeal.getOpen(), restaurantDeal.getStart());
    }

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
