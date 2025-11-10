package com.github.squirrelgrip.deals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.squirrelgrip.deals.domain.Restaurants;
import com.github.squirrelgrip.deals.repository.RestaurantRepository;

import java.io.IOException;
import java.io.InputStream;

public class RestaurantsLoader {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final RestaurantRepository restaurantRepository;

    public RestaurantsLoader(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void load(String data) {
        try {
            Restaurants restaurants = objectMapper.readValue(data, Restaurants.class);
            restaurants.getRestaurants().forEach(restaurantRepository::save);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFromResources(String resource) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource)) {
            loadFromInputStream(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFromInputStream(InputStream inputStream) {
        try {
            Restaurants restaurants = objectMapper.readValue(inputStream, Restaurants.class);
            restaurants.getRestaurants().forEach(restaurantRepository::save);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
