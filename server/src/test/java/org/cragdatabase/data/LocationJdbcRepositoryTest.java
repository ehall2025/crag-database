package org.cragdatabase.data;

import org.cragdatabase.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LocationJdbcRepositoryTest {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private JdbcClient jdbcClient;

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Test
    void shouldFindAllLocations() {
        List<Location> actual = repository.findAllLocations();

        assertEquals(2, actual.size());
        assertEquals("wisconsin", actual.get(0).getRegion().toLowerCase());
        assertEquals("minnesota", actual.get(1).getRegion().toLowerCase());
    }
}