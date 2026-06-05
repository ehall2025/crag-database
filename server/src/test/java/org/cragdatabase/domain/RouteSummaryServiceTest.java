package org.cragdatabase.domain;

import org.cragdatabase.data.RouteSummaryRepository;
import org.cragdatabase.domain.results.Result;
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
        RouteSummary r1 = new RouteSummary(1, 1, 1, 5, 5, 1);
        RouteSummary r2 = new RouteSummary(2, 1, 2, 6, 4, 0);
        RouteSummary r3 = new RouteSummary(3, 1, 3, 4, 3, 3);

        List<RouteSummary> returnedSummaries = List.of(r1, r2, r3);

        int expectedDifficulty = (r1.getDifficultyRating() + r2.getDifficultyRating() + r3.getDifficultyRating()) / returnedSummaries.size();
        int expectedQuality = (r1.getQualityRating() + r2.getQualityRating() + r3.getQualityRating()) / returnedSummaries.size();
        int expectedDanger = Math.max(Math.max(r1.getDangerRating(), r2.getDangerRating()), r3.getDangerRating());

        RouteSummary expected = new RouteSummary(1, 1, 1, expectedDifficulty, expectedQuality, expectedDanger);

        when(repository.findByRouteId(anyInt())).thenReturn(returnedSummaries);

        Result<RouteSummary> actual = service.findByRouteId(1);

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getpayload());
    }

    @Test
    void shouldAddSummary() {
        RouteSummary toAdd = new RouteSummary(0, 1, 1, 5, 5, 1);

        when(repository.add(any())).thenReturn(true);

        Result<RouteSummary> actual = service.add(toAdd);

        assertTrue(actual.isSuccess());
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
    void shouldNotAddNegativeDifficulty() {
        //Test negative difficulty
        RouteSummary toAdd = new RouteSummary(0, 1, 1, -1, 5, 1);

        Result<RouteSummary> actual = service.add(toAdd);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddNegativeQuality() {//Test negative quality
        RouteSummary toAdd = new RouteSummary(0, 1, 1, 5, -1, 1);

        Result<RouteSummary> actual = service.add(toAdd);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddNegativeDanger() {
        //Test negative danger
        RouteSummary toAdd = new RouteSummary(0, 1, 1, 5, 5, -1);

        Result<RouteSummary> actual = service.add(toAdd);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldUpdateSummary() {
        RouteSummary toUpdate = new RouteSummary(1, 1, 1, 7, 5, 1);

        when(repository.update(any())).thenReturn(true);

        Result<RouteSummary> actual = service.update(toUpdate);

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
    void shouldNotUpdateNegativeDifficulty() {
        //Test negative difficulty
        RouteSummary toUpdate = new RouteSummary(0, 1, 1, -1, 5, 1);

        Result<RouteSummary> actual = service.update(toUpdate);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotUpdateNegativeQuality() {
        //Test negative quality
        RouteSummary toUpdate = new RouteSummary(0, 1, 1, 5, -1, 1);

        Result<RouteSummary> actual = service.update(toUpdate);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotUpdateNegativeDanger() {
        //Test negative danger
        RouteSummary toUpdate = new RouteSummary(0, 1, 1, 5, 5, -1);

        Result<RouteSummary> actual = service.update(toUpdate);

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