package org.cragdatabase.data;

import org.cragdatabase.models.Route;

import java.util.List;

public interface UserProfileRepository {

    List<Route> addListEntry(int listId, int routeId);

    List<Route> removeListEntry(int listId, int routeId);
}
