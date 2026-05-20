package org.cragdatabase.domain;

import org.cragdatabase.data.RouteRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private final RouteRepository routeRepository;


    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Result<Route> userPost(Route route) {
        return null;
    }

    public Result<Route> adminPost(Route route) {
        return null;
    }

    public Result<Route> adminPut(Route route) {
        return null;
    }

    public Result<Route> adminDelete(int routeId) {
        return null;
    }

}
