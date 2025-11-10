package com.github.squirrelgrip.deals;

import com.github.squirrelgrip.deals.loader.RestaurantsLoader;
import com.github.squirrelgrip.deals.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantsLoaderTest {

    private RestaurantRepository restaurantRepository = new RestaurantRepository();

    private RestaurantsLoader testSubject;

    @BeforeEach
    void beforeEach() {
        testSubject = new RestaurantsLoader(restaurantRepository);
    }

    @Test
    void load_GivenStringWithNoRestaurants() {
        testSubject.load("{\"restaurants\": []}");

        assertThat(restaurantRepository.size()).isEqualTo(0);
    }

    @Test
    void load_GivenResourceWith6Restaurants() {
        testSubject.loadFromResources("data.json");

        assertThat(restaurantRepository.size()).isEqualTo(6);
    }
}
