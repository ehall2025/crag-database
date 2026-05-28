package org.cragdatabase.data;

import org.cragdatabase.models.Crag;
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

    private final int VALID_ID = 1;
    private final int INVALID_ID = 999;

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

    @Test
    void shouldFindLocationById() {
        Location actual = repository.findLocationById(VALID_ID);

        assertNotNull(actual);
        assertEquals("wisconsin", actual.getRegion().toLowerCase());
        assertFalse(actual.getCrags().isEmpty());
    }

    @Test
    void shouldNotFindLocationById() {
        Location actual = repository.findLocationById(INVALID_ID);

        assertNull(actual);
    }

    @Test
    void shouldFindCragsByLocation() {
        List<Crag> actual = repository.findCragsByLocation(VALID_ID);

        assertEquals(1, actual.size());
        assertEquals("Devil's Lake", actual.get(0).getName());
    }

    @Test
    void shouldFindCragById() {
        Crag actual = repository.findCragById(VALID_ID);

        assertNotNull(actual);
        assertEquals("Devil's Lake", actual.getName());
        assertFalse(actual.getAreas().isEmpty());
    }

    @Test
    void shouldNotFindCragById() {
        Crag actual = repository.findCragById(INVALID_ID);

        assertNull(actual);
    }

    
}