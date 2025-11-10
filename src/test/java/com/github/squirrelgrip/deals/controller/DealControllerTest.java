package com.github.squirrelgrip.deals.controller;

import com.github.squirrelgrip.deals.domain.Deals;
import com.github.squirrelgrip.deals.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.format.DateTimeParseException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DealControllerTest {

    @Mock
    private DealService dealService;

    private DealController testSubject;

    @BeforeEach
    void beforeEach() {
        testSubject = new DealController(dealService);
    }

    @Test
    void getDealsAt() {
        Deals deals = new Deals(Collections.emptyList());

        given(dealService.getDealsAt("15:00")).willReturn(deals);

        testSubject.getDealsAt("15:00");

        verify(dealService).getDealsAt("15:00");
    }

    @Test
    void getDealsAt_GivenTimeIsInvalid() {
        given(dealService.getDealsAt("ABCD")).willThrow(new DateTimeParseException("Invalid Time.", "ABCD", 1));

        assertThatThrownBy(() -> testSubject.getDealsAt("ABCD"))
                .isInstanceOf(DateTimeParseException.class)
                .hasMessage("Invalid Time.");
    }

}