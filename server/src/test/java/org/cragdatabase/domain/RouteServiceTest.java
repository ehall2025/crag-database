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
import static org.mockito.ArgumentMatchers.*;
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
        Route toAdd = new Route(0, "Cave Traverse", 3, "description", "start");

        when(repository.postRoute(any(), eq("Route_Staging"))).thenReturn(true);

        assertTrue(service.userPost(toAdd).isSuccess());
    }

    @Test
    void shouldNotPostStagedRouteNoName() {
        Route toAdd = new Route(0, "", 3, "description", "start");

        assertFalse(service.userPost(toAdd).isSuccess());
    }

    @Test
    void shouldNotPostStagedRouteNoDescription() {
        Route toAdd = new Route(0, "Cave Traverse", 3, "", "start");

        assertFalse(service.userPost(toAdd).isSuccess());
    }

    @Test
    void shouldNotPostStagedRouteNoStartPosition() {
        Route toAdd = new Route(0, "Cave Traverse", 3, "description", "");

        assertFalse(service.userPost(toAdd).isSuccess());
    }

    @Test
    void shouldNotPostStagedRouteBadArea() {
        Route toAdd = new Route(0, "", 0, "description", "start");

        assertFalse(service.userPost(toAdd).isSuccess());
    }

    @Test
    void shouldPostRoute() {
        Route toAdd = new Route(0, "Cave Traverse", 3, "description", "start");

        when(repository.postRoute(any(), eq("Route"))).thenReturn(true);
        when(repository.adminDeleteStagedRoute(anyInt())).thenReturn(true);

        assertTrue(service.adminPost(toAdd).isSuccess());
    }

    @Test
    void shouldNotPostRouteNoName() {
        Route toAdd = new Route(0, "", 3, "description", "start");

        assertFalse(service.adminPost(toAdd).isSuccess());
    }

    @Test
    void shouldNotPostRouteNoDescription() {
        Route toAdd = new Route(0, "Cave Traverse", 3, "", "start");

        assertFalse(service.adminPost(toAdd).isSuccess());
    }

    @Test
    void shouldNotPostRouteNoStartPosition() {
        Route toAdd = new Route(0, "Cave Traverse", 3, "description", "");

        assertFalse(service.adminPost(toAdd).isSuccess());
    }

    @Test
    void shouldNotPostRouteBadArea() {
        Route toAdd = new Route(0, "Cave Traverse", 0, "description", "start");

        assertFalse(service.adminPost(toAdd).isSuccess());
    }

    @Test
    void shouldUpdateRoute() {
        Route toUpdate = new Route(1, "Dagger of the Lake", 2, "description", "start");

        when(repository.adminUpdateRoute(any())).thenReturn(true);

        assertTrue(service.adminPut(toUpdate).isSuccess());
    }

    @Test
    void shouldNotUpdateRouteNoName() {
        Route toUpdate = new Route(0, "", 3, "description", "start");

        assertFalse(service.adminPut(toUpdate).isSuccess());
    }

    @Test
    void shouldNotUpdateRouteNoDescription() {
        Route toUpdate = new Route(0, "Cave Traverse", 3, "", "start");

        assertFalse(service.adminPut(toUpdate).isSuccess());
    }

    @Test
    void shouldNotUpdateRouteNoStartPosition() {
        Route toUpdate = new Route(0, "Cave Traverse", 3, "description", "");

        assertFalse(service.adminPut(toUpdate).isSuccess());
    }

    @Test
    void shouldNotUpdateRouteBadArea() {
        Route toUpdate = new Route(0, "", 0, "description", "start");

        assertFalse(service.adminPut(toUpdate).isSuccess());
    }

    @Test
    void shouldDelete() {

    }

    @Test
    void shouldNotDelete() {

    }

}