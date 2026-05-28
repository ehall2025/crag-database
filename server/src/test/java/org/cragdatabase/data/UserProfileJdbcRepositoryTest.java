package org.cragdatabase.data;

import org.cragdatabase.models.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserProfileJdbcRepositoryTest {

    @Autowired
    UserProfileRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void addListEntryShouldReturnUpdatedListWhenSuccessful() {
        List<Route> result = repository.addListEntry(3, 1);

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