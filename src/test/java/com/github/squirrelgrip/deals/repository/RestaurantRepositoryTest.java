package com.github.squirrelgrip.deals.repository;

import com.github.squirrelgrip.deals.loader.RestaurantsLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantRepositoryTest {

    private RestaurantRepository testSubject;

    @BeforeEach
    void beforeEach() {
        testSubject = new RestaurantRepository();
        new RestaurantsLoader(testSubject).loadFromResources("data.json");
    }

    @Test
    void streamAll() {
        assertThat(testSubject.streamAll()).hasSize(6);
    }
}