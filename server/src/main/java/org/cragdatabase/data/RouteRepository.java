package org.cragdatabase.data;

import org.cragdatabase.models.Route;

import java.util.List;

public interface RouteRepository {

    boolean userPostRoute(Route route);

    boolean adminPostRoute(Route route);

    boolean adminDeleteStagedRoute(int stagedRouteId);

    boolean adminUpdateRoute(Route route);

    boolean adminDeleteRoute(int routeId);

    List<Route> getStagedRoutes();
}
