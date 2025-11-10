package com.github.squirrelgrip.deals.service;

import com.github.squirrelgrip.deals.domain.Deal;
import com.github.squirrelgrip.deals.repository.RestaurantRepository;

import java.util.stream.Stream;

public class DealService {
    private final RestaurantRepository restaurantRepository;

    public DealService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Stream<Deal> streamAllDeals() {
        return restaurantRepository.streamAll().flatMap(restaurant ->
                restaurant.getDeals()
                        .stream()
                        .map(restaurantDeal ->
                                new Deal(
                                        restaurant.getObjectId(),
                                        restaurant.getName(),
                                        restaurant.getAddress1(),
                                        restaurant.getSuburb(),
                                        restaurant.getOpen(),
                                        restaurant.getClose(),
                                        restaurantDeal.getObjectId(),
                                        restaurantDeal.getDiscount(),
                                        restaurantDeal.getDineIn(),
                                        restaurantDeal.getLightning()
                                )
                        )
        );
    }
}
