package com.github.squirrelgrip.deals.service;

import com.github.squirrelgrip.deals.domain.Deal;
import com.github.squirrelgrip.deals.domain.Deals;
import com.github.squirrelgrip.deals.domain.Interval;
import com.github.squirrelgrip.deals.domain.Peak;
import com.github.squirrelgrip.deals.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
public class DealService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DealService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Stream<Deal> streamAllDeals() {
        return restaurantRepository.streamAll().flatMap(restaurant ->
                restaurant.getDeals()
                        .stream()
                        .map(restaurantDeal ->
                                restaurantDeal.getRealDeal(restaurant)
                        )
        );
    }

    public Deals getDealsAt(String time) {
        LocalTime localTime = LocalTime.parse(time);
        return new Deals(
                streamAllDeals()
                        .filter(deal -> deal.isValidAt(localTime))
                        .toList()
        );
    }

    public Peak getPeak() {
        Interval interval = getIntervals().stream()
                .max(Comparator.comparing(Interval::getCount))
                .orElseGet(() -> new Interval(LocalTime.MIN, LocalTime.MIN, 0));
        return new Peak(interval.getTimeStart(), interval.getTimeEnd());
    }

    private List<Interval> getIntervals() {
        TreeMap<LocalTime, AtomicInteger> timeCount = new TreeMap<>();
        timeCount.put(LocalTime.MIN, new AtomicInteger(0));
        timeCount.put(LocalTime.MAX, new AtomicInteger(0));
        streamAllDeals()
                .forEach(deal -> {
                            timeCount.computeIfAbsent(
                                    deal.getDealStart(),
                                    (LocalTime) -> new AtomicInteger(0)
                            ).incrementAndGet();
                            timeCount.computeIfAbsent(
                                    deal.getDealEnd(),
                                    (LocalTime) -> new AtomicInteger(0)
                            ).decrementAndGet();
                        }
                );
        List<Map.Entry<LocalTime, AtomicInteger>> entryList = timeCount.entrySet().stream().toList();
        ArrayList<Interval> intervals = new ArrayList<>();
        int currentCount = 0;
        for (int i = 0; i < entryList.size() - 2; i++) {
            LocalTime startTime = entryList.get(i).getKey();
            LocalTime endTime = entryList.get(i + 1).getKey();
            currentCount = currentCount + entryList.get(i).getValue().get();
            intervals.add(new Interval(startTime, endTime, currentCount));
        }
        return intervals;
    }
}
