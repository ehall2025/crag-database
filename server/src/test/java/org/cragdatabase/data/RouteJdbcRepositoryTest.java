package org.cragdatabase.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RouteJdbcRepositoryTest {

    @Autowired
    private RouteRepository repository;

    @Autowired
    private JdbcClient jdbcClient;

    @Test
    void shouldFindStagedRoutes() {

    }

    @Test
    void shouldPostToRouteStaging() {

    }

    @Test
    void shouldPostToRoute() {

    }
}