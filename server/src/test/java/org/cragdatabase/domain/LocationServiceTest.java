package org.cragdatabase.domain;

import org.cragdatabase.TestHelper;
import org.cragdatabase.data.LocationRepository;
import org.cragdatabase.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LocationServiceTest {
    @Autowired
    LocationService service;

    @MockBean
    LocationRepository repository;

    @Test
    void shouldFindAllLocations() {
        List<Location> expected = TestHelper.getLocations();

        when(repository.findAllLocations()).thenReturn(TestHelper.getLocations());

        List<Location> actual = service.findAllLocations();

        assertEquals(expected, actual);
    }

}