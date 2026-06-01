package org.cragdatabase.domain;

import org.cragdatabase.data.RouteRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private final RouteRepository routeRepository;


    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Result<Route> userPost(Route route) {
        Result<Route> result = validateRoute(route);

        if (!result.isSuccess()) return result;

        if (!routeRepository.postRoute(route, "Route_Staging")) {
            result.addErrorMessage("unable to add route", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Route> adminPost(Route route) {
        Result<Route> result = validateRoute(route);

        if (!result.isSuccess()) return result;

        if (routeRepository.postRoute(route, "Route")) {
            if(!routeRepository.adminDeleteStagedRoute(route.getId())) {
                result.addErrorMessage("could not find staged route to delete", ResultType.NOT_FOUND);
            }
        } else {
            result.addErrorMessage("unable to add route", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Route> adminPut(Route route) {
        Result<Route> result = validateRoute(route);

        if (!result.isSuccess()) return result;

        if (!routeRepository.adminUpdateRoute(route)) {
            result.addErrorMessage("unable to update route", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result adminDelete(int routeId) {
        Result result = new Result();

        if(!routeRepository.adminDeleteStagedRoute(routeId)) {
            result.addErrorMessage("could not find route to delete", ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<Route> validateRoute (Route route) {
        Result<Route> result = new Result<>();

        if (route.getName().isBlank() || route.getDescription().isBlank() || route.getStartPosition().isBlank()) {
            result.addErrorMessage("Name, Description, and Start Position are all required fields", ResultType.INVALID);
        }

        if (route.getAreaId() <= 0) {
            result.addErrorMessage("valid Area field is required", ResultType.INVALID);
        }

        return result;
    }

    public Result<List<Route>> getStagedRoutes() {
        Result<List<Route>> result = new Result<>();

        result.setpayload(routeRepository.getStagedRoutes());

        return result;
    }
}
