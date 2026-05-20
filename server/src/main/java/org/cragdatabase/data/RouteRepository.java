package org.cragdatabase.data;

import org.cragdatabase.models.Route;

public interface RouteRepository {

    Route userPostRoute(Route route);

    Route adminPostRoute(Route route);

    Route adminUpdateRoute(Route route);

    boolean adminDeleteRoute(int routeId);
}
