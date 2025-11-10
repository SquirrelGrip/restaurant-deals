package com.github.squirrelgrip.deals.repository;

import com.github.squirrelgrip.deals.domain.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository {
    private final Map<String, Restaurant> restaurants = new HashMap<>();

    public void save(Restaurant restaurant) {
        restaurants.put(restaurant.getObjectId(), restaurant);
    }

    public int size() {
        return restaurants.size();
    }
}
