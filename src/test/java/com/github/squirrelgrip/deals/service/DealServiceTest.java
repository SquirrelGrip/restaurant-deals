package com.github.squirrelgrip.deals.service;

import com.github.squirrelgrip.deals.RestaurantsLoader;
import com.github.squirrelgrip.deals.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealServiceTest {
    private DealService testSubject;
    private final RestaurantRepository restaurantRepository = new RestaurantRepository();

    @BeforeEach
    void beforeEach() {
        new RestaurantsLoader(restaurantRepository).loadFromResources("data.json");
        testSubject = new DealService(restaurantRepository);
    }

    @Test
    void streamAllDeals() {
        assertThat(testSubject.streamAllDeals()).hasSize(11);
    }

    @Test
    void streamAllDealsAt3pm() {
        assertThat(testSubject.streamAllDealsAt("15:00")).hasSize(7);
    }

    @Test
    void streamAllDealsAt6pm() {
        assertThat(testSubject.streamAllDealsAt("18:00")).hasSize(9);
    }

    @Test
    void streamAllDealsAt9pm() {
        assertThat(testSubject.streamAllDealsAt("21:00")).hasSize(9);
    }
}
