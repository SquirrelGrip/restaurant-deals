package com.github.squirrelgrip.deals.repository;

import com.github.squirrelgrip.deals.domain.Deal;
import com.github.squirrelgrip.deals.domain.Restaurant;
import com.github.squirrelgrip.deals.loader.RestaurantsLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public class RestaurantRepository {
    private final Map<String, Restaurant> restaurants = new HashMap<>();

    @PostConstruct
    public void initialize() {
        new RestaurantsLoader(this).loadFromResources("data.json");
    }

    public void save(Restaurant restaurant) {
        restaurants.put(restaurant.getObjectId(), restaurant);
    }

    public int size() {
        return restaurants.size();
    }

    public Stream<Restaurant> streamAll() {
        return restaurants.values().stream();
    }
}
