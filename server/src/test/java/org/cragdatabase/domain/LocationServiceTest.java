package org.cragdatabase.domain;

import org.cragdatabase.TestHelper;
import org.cragdatabase.data.LocationRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.Area;
import org.cragdatabase.models.Crag;
import org.cragdatabase.models.Location;
import org.cragdatabase.models.Route;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LocationServiceTest {

    private final int VALID_ID = 1;

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

    @Test
    void shouldFindLocationById() {
        when(repository.findLocationById(anyInt())).thenReturn(new Location());

        Result actual = service.findById(VALID_ID, "location");

        verify(repository).findLocationById(VALID_ID);
        assertNotNull(actual.getpayload());
    }

    @Test
    void shouldFindCragById() {
        when(repository.findCragById(anyInt())).thenReturn(new Crag());

        Result actual = service.findById(VALID_ID, "crag");

        verify(repository).findCragById(VALID_ID);
        assertNotNull(actual.getpayload());
    }

    @Test
    void shouldFindAreaById() {
        when(repository.findAreaById(anyInt())).thenReturn(new Area());

        Result actual = service.findById(VALID_ID, "area");

        verify(repository).findAreaById(VALID_ID);
        assertNotNull(actual.getpayload());
    }

    @Test
    void shouldFindRouteById() {
        when(repository.findRouteById(anyInt())).thenReturn(new Route());

        Result actual = service.findById(VALID_ID, "route");
        
        verify(repository).findRouteById(VALID_ID);
        assertNotNull(actual.getpayload());
    }

}