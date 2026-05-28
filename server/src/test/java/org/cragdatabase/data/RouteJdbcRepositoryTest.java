package org.cragdatabase.data;

import org.cragdatabase.models.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RouteJdbcRepositoryTest {

    @Autowired
    private RouteRepository repository;

    @Autowired
    private JdbcClient jdbcClient;

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Test
    void shouldFindStagedRoutes() {
        List<Route> actual = repository.getStagedRoutes();

        assertEquals(2, actual.size());
        assertEquals("Panic Room", actual.get(0).getName());
        assertEquals("Ghostly Grips", actual.get(1).getName());
    }

    @Test
    void shouldPostToRouteStaging() {

    }

    @Test
    void shouldPostToRoute() {

    }

    @Test
    void shouldUpdateRoute() {

    }

    @Test
    void shouldNotUpdateRoute() {

    }

    @Test
    void shouldDeleteRoute() {

    }

    @Test
    void shouldNotDeleteRoute() {

    }

    @Test
    void shouldDeleteStagedRoute() {

    }

    @Test
    void shouldNotDeleteStagedRoute() {

    }
}