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
        Route expected = new Route(3, "Cave Traverse", 3, "", "");
        Route toAdd = new Route(0, "Cave Traverse", 3, "", "");

        assertTrue(repository.postRoute(toAdd, "Route_Staging"));

        Route actual = repository.getStagedRoutes().get(2);

        assertEquals(expected, actual);
    }

    @Test
    void shouldPostToRoute() {
        Route expected = new Route(3, "Cave Traverse", 3, "", "");
        Route toAdd = new Route(0, "Cave Traverse", 3, "", "");

        assertTrue(repository.postRoute(toAdd, "Route"));
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