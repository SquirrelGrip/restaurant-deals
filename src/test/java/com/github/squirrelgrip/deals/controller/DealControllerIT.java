package com.github.squirrelgrip.deals.controller;

import com.github.squirrelgrip.deals.domain.Deals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DealControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/dealsAt?timeOfDay=15:00", Deals.class).deals()).hasSize(7);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/dealsAt?timeOfDay=18:00", Deals.class).deals()).hasSize(9);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/dealsAt?timeOfDay=21:00", Deals.class).deals()).hasSize(9);
    }
}

