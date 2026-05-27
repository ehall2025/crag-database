package org.cragdatabase.data;

import org.cragdatabase.models.Route;

import java.util.List;

public interface RouteRepository {

    boolean postRoute(Route route, String table);

    boolean adminDeleteStagedRoute(int stagedRouteId);

    boolean adminUpdateRoute(Route route);

    boolean adminDeleteRoute(int routeId);

    List<Route> getStagedRoutes();
}
