package org.cragdatabase.data;

import org.cragdatabase.models.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserProfileJdbcRepositoryTest {

    @Autowired
    UserProfileRepository repository;

    @Autowired
    JdbcClient jdbcClient;

    @BeforeEach
    void setup() {
        jdbcClient.sql("call set_known_good_state();").update();
    }

    @Test
    void addListEntryShouldReturnUpdatedListWhenSuccessful() {
        List<Route> result = repository.addListEntry(2, 1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(r -> r.getId() == 1));
    }
    
    @Test
    void removeListEntryShouldReturnUpdatedListWhenSuccessful() {
        List<Route> result = repository.removeListEntry(4, 2);

        assertNotNull(result);
        assertTrue(result.stream().noneMatch(r -> r.getId() == 2));
    }

    @Test
    void removeListEntryShouldReturnEmptyListWhenEntryNotFound() {
        List<Route> result = repository.removeListEntry(3, 99);

        assertTrue(result.isEmpty());
    }
}