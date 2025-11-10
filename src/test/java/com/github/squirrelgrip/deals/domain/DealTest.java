package com.github.squirrelgrip.deals.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DealTest {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mma");

    @Mock
    private Restaurant restaurant;

    @Mock
    private RestaurantDeal restaurantDeal;

    private Deal testSubject;

    @Test
    void getDealStart() {
        testSubject = new Deal(restaurant, restaurantDeal);
        given(restaurant.getOpen()).willReturn(LocalTime.parse("3:00pm", dateTimeFormatter));
        assertThat(testSubject.getDealStart()).isEqualTo(restaurant.getOpen());

        given(restaurantDeal.getOpen()).willReturn(LocalTime.parse("4:00pm", dateTimeFormatter));
        assertThat(testSubject.getDealStart()).isEqualTo(restaurantDeal.getOpen());

        given(restaurantDeal.getStart()).willReturn(LocalTime.parse("5:00pm", dateTimeFormatter));
        assertThat(testSubject.getDealStart()).isEqualTo(restaurantDeal.getStart());

        given(restaurant.getOpen()).willReturn(LocalTime.parse("6:00pm", dateTimeFormatter));
        assertThat(testSubject.getDealStart()).isEqualTo(restaurant.getOpen());
    }

    @Test
    void getDealEnd() {
        testSubject = new Deal(restaurant, restaurantDeal);
        given(restaurant.getClose()).willReturn(LocalTime.parse("4:30pm", dateTimeFormatter));
        assertThat(testSubject.getDealEnd()).isEqualTo(restaurant.getClose());

        given(restaurantDeal.getClose()).willReturn(LocalTime.parse("4:00pm", dateTimeFormatter));
        assertThat(testSubject.getDealEnd()).isEqualTo(restaurantDeal.getClose());

        given(restaurantDeal.getEnd()).willReturn(LocalTime.parse("3:30pm", dateTimeFormatter));
        assertThat(testSubject.getDealEnd()).isEqualTo(restaurantDeal.getEnd());

        given(restaurant.getClose()).willReturn(LocalTime.parse("3:00pm", dateTimeFormatter));
        assertThat(testSubject.getDealEnd()).isEqualTo(restaurant.getClose());
    }

    @Test
    void isValidAt() {
        testSubject = new Deal(restaurant, restaurantDeal);
        given(restaurantDeal.getStart()).willReturn(LocalTime.parse("3:00pm", dateTimeFormatter));
        given(restaurantDeal.getEnd()).willReturn(LocalTime.parse("5:00pm", dateTimeFormatter));

        assertThat(testSubject.isValidAt(LocalTime.parse("2:00pm", dateTimeFormatter))).isFalse();
        assertThat(testSubject.isValidAt(LocalTime.parse("3:00pm", dateTimeFormatter))).isTrue();
        assertThat(testSubject.isValidAt(LocalTime.parse("4:00pm", dateTimeFormatter))).isTrue();
        assertThat(testSubject.isValidAt(LocalTime.parse("5:00pm", dateTimeFormatter))).isTrue();
        assertThat(testSubject.isValidAt(LocalTime.parse("6:00pm", dateTimeFormatter))).isFalse();
    }

    @Test
    void isValidAt_GivenRestaurantIsOnlyOpenAt4pm() {
        testSubject = new Deal(restaurant, restaurantDeal);
        given(restaurantDeal.getStart()).willReturn(LocalTime.parse("3:00pm", dateTimeFormatter));
        given(restaurantDeal.getEnd()).willReturn(LocalTime.parse("5:00pm", dateTimeFormatter));
        given(restaurant.getOpen()).willReturn(LocalTime.parse("4:00pm", dateTimeFormatter));
        given(restaurant.getClose()).willReturn(LocalTime.parse("6:00pm", dateTimeFormatter));

        assertThat(testSubject.isValidAt(LocalTime.parse("2:00pm", dateTimeFormatter))).isFalse();
        assertThat(testSubject.isValidAt(LocalTime.parse("3:00pm", dateTimeFormatter))).isFalse();
        assertThat(testSubject.isValidAt(LocalTime.parse("4:00pm", dateTimeFormatter))).isTrue();
        assertThat(testSubject.isValidAt(LocalTime.parse("5:00pm", dateTimeFormatter))).isTrue();
        assertThat(testSubject.isValidAt(LocalTime.parse("6:00pm", dateTimeFormatter))).isFalse();
        assertThat(testSubject.isValidAt(LocalTime.parse("7:00pm", dateTimeFormatter))).isFalse();
    }

}