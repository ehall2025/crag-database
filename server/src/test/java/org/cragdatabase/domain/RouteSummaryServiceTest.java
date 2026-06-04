package org.cragdatabase.domain;

import org.cragdatabase.data.RouteSummaryRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.Route;
import org.cragdatabase.models.RouteSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RouteSummaryServiceTest {

    @Autowired
    RouteSummaryService service;

    @MockBean
    RouteSummaryRepository repository;

    @Test
    void shouldFindByRouteId() {
        List<RouteSummary> returnedSummaries = List.of(
                new RouteSummary(1, 1, 1, 5, 5, 1),
                new RouteSummary(2, 1, 2, 6, 4, 0),
                new RouteSummary(3, 1, 3, 4, 3, 3)
        );

        RouteSummary expected = new RouteSummary(1, 1, 1, 5, 4, 3);

        when(repository.findByRouteId(anyInt())).thenReturn(returnedSummaries);

        Result<RouteSummary> actual = service.findByRouteId(1);

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getpayload());
    }

    @Test
    void shouldAddSummary() {
        RouteSummary toAdd = new RouteSummary(0, 1, 1, 5, 5, 1);
        RouteSummary expected = new RouteSummary(1, 1, 1, 5, 5, 1);

        when(repository.add(any())).thenReturn(expected);

        Result<RouteSummary> actual = service.add(toAdd);

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getpayload());
    }

    @Test
    void shouldNotAddInvalidIds() {
        RouteSummary toAdd = new RouteSummary(0, 0, 1, 5, 5, 1);

        Result<RouteSummary> actual = service.add(toAdd);

        assertFalse(actual.isSuccess());

        toAdd = new RouteSummary(0, 1, 0, 5, 5, 1);

        actual = service.add(toAdd);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddNegativeRatings() {
        //Test negative difficulty
        RouteSummary toAdd = new RouteSummary(0, 1, 1, -1, 5, 1);

        Result<RouteSummary> actual = service.add(toAdd);

        assertFalse(actual.isSuccess());

        //Test negative quality
        toAdd = new RouteSummary(0, 1, 1, 5, -1, 1);

        actual = service.add(toAdd);

        assertFalse(actual.isSuccess());

        //Test negative danger
        toAdd = new RouteSummary(0, 1, 1, 5, 5, -1);

        actual = service.add(toAdd);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldUpdateSummary() {
        RouteSummary toUpdate = new RouteSummary(1, 1, 1, 7, 5, 1);

        when(repository.update(any())).thenReturn(true);

        Result<RouteSummary> actual = service.add(toUpdate);

        assertTrue(actual.isSuccess());
    }

    @Test
    void shouldNotUpdateInvalidIds() {
        RouteSummary toUpdate = new RouteSummary(1, 0, 1, 5, 5, 1);

        Result<RouteSummary> actual = service.update(toUpdate);

        assertFalse(actual.isSuccess());

        toUpdate = new RouteSummary(1, 1, 0, 5, 5, 1);

        actual = service.update(toUpdate);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotUpdateNegativeRatings() {
        //Test negative difficulty
        RouteSummary toUpdate = new RouteSummary(0, 1, 1, -1, 5, 1);

        Result<RouteSummary> actual = service.update(toUpdate);

        assertFalse(actual.isSuccess());

        //Test negative quality
        toUpdate = new RouteSummary(0, 1, 1, 5, -1, 1);

        actual = service.update(toUpdate);

        assertFalse(actual.isSuccess());

        //Test negative danger
        toUpdate = new RouteSummary(0, 1, 1, 5, 5, -1);

        actual = service.update(toUpdate);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldDelete() {
        when(repository.deleteById(anyInt())).thenReturn(true);
        assertTrue(service.deleteById(1));
    }

    @Test
    void shouldNotDelete() {
        when(repository.deleteById(anyInt())).thenReturn(true); //return true to test only passes if service catches by ID
        assertFalse(service.deleteById(-1));
    }

}