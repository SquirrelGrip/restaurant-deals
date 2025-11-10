package com.github.squirrelgrip.deals.domain;

import com.github.squirrelgrip.deals.RestaurantsLoader;
import com.github.squirrelgrip.deals.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantsLoaderTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    private RestaurantsLoader testSubject;

    @BeforeEach
    void beforeEach() {
        testSubject = new RestaurantsLoader(restaurantRepository);
    }

    @Test
    void load_GivenStringWithNoRestaurants() {
        testSubject.load("{\"restaurants\": []}");
        verify(restaurantRepository, never()).save(isA(Restaurant.class));
    }

    @Test
    void load_GivenResourceWith6Restaurants() {
        testSubject.loadFromResources("data.json");
        verify(restaurantRepository, times(6)).save(isA(Restaurant.class));
    }
}
