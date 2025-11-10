package com.github.squirrelgrip.deals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.squirrelgrip.deals.domain.Restaurants;

import java.io.InputStream;

public class RestaurantsLoader {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Restaurants load(String data) {
        try {
            return objectMapper.readValue(data, Restaurants.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Restaurants loadFromResources(String resource) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource)) {
            return objectMapper.readValue(inputStream, Restaurants.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
