package org.cragdatabase.domain;

import org.cragdatabase.data.RouteRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.Route;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RouteServiceTest {

    @Autowired
    RouteService service;

    @MockBean
    RouteRepository repository;

    @Test
    void shouldFindStagedRoutes() {
        when(repository.getStagedRoutes()).thenReturn(List.of());
        Result<List<Route>> actual = service.getStagedRoutes();
        assertEquals(0, actual.getpayload().size());
    }

    @Test
    void shouldPostStagedRoute() {

    }

    @Test
    void shouldNotPostStagedRouteNoName() {

    }

    @Test
    void shouldNotPostStagedRouteNoDescription() {

    }

    @Test
    void shouldNotPostStagedRouteNoStartPosition() {

    }

    @Test
    void shouldNotPostStagedRouteBadArea() {

    }

    @Test
    void shouldPostRoute() {

    }

    @Test
    void shouldNotPostRouteNoName() {

    }

    @Test
    void shouldNotPostRouteNoDescription() {

    }

    @Test
    void shouldNotPostRouteNoStartPosition() {

    }

    @Test
    void shouldNotPostRouteBadArea() {

    }

    @Test
    void shouldUpdateRoute() {

    }

    @Test
    void shouldNotUpdateRouteNoName() {

    }

    @Test
    void shouldNotUpdateRouteNoDescription() {

    }

    @Test
    void shouldNotUpdateRouteNoStartPosition() {

    }

    @Test
    void shouldNotUpdateRouteBadArea() {

    }

    @Test
    void shouldDelete() {

    }

    @Test
    void shouldNotDelete() {

    }

}