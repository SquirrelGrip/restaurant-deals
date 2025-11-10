package com.github.squirrelgrip.deals.domain;

import com.github.squirrelgrip.deals.RestaurantsLoader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantsLoaderTest {

    @Test
    void load_GivenStringWithNoRestaurants() {
        RestaurantsLoader testSubject = new RestaurantsLoader();
        Restaurants restaurants = testSubject.load("{\"restaurants\": []}");
        assertThat(restaurants.getRestaurants()).isEmpty();
    }

    @Test
    void load_GivenResourceWith6Restaurants() {
        RestaurantsLoader testSubject = new RestaurantsLoader();
        Restaurants restaurants = testSubject.loadFromResources("data.json");
        assertThat(restaurants.getRestaurants()).hasSize(6);
    }
}
