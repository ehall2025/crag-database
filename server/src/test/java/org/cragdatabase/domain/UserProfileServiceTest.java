package org.cragdatabase.domain;

import org.cragdatabase.data.UserProfileRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.Route;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserProfileServiceTest {

    @Autowired
    UserProfileService service;

    @MockBean
    UserProfileRepository userProfileRepository;

    @Test
    void shouldAddListEntry() {
        List<Route> routes = List.of(new Route(1, "A", 2, "B", "C"));
        when(userProfileRepository.addListEntry(1, 1)).thenReturn(routes);

        Result<List<Route>> actual = service.addListEntry(1, 1);
        assertEquals(ResultType.SUCCESS, actual.getResultType());
        assertEquals(routes, actual.getpayload());
    }

    @Test
    void shouldNotAddListEntryWhenNotFound() {
        when(userProfileRepository.addListEntry(1, 999)).thenReturn(null);

        Result<List<Route>> actual = service.addListEntry(1, 999);
        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
    }

    @Test
    void shouldRemoveListEntry() {
        List<Route> routes = List.of(new Route(1, "A", 2, "B", "C"));
        when(userProfileRepository.removeListEntry(1, 1)).thenReturn(routes);

        Result<List<Route>> actual = service.removeListEntry(1, 1);
        assertEquals(ResultType.SUCCESS, actual.getResultType());
        assertEquals(routes, actual.getpayload());
    }

    @Test
    void shouldNotRemoveListEntryWhenNotFound() {
        when(userProfileRepository.removeListEntry(1, 999)).thenReturn(null);

        Result<List<Route>> actual = service.removeListEntry(1, 999);
        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
    }
}