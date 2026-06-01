package org.cragdatabase.domain;

import org.cragdatabase.data.UserProfileRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    @Autowired
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public Result<List<Route>> addListEntry(int listId, int routeId) {
        Result<List<Route>> result = new Result<>();

        if (listId <= 0 || routeId <= 0) {
            result.addErrorMessage("ids must be greater than or equal to 1", ResultType.INVALID);
            return result;
        }

        List<Route> existingRoutes = userProfileRepository.findRouteList(listId);
        for (Route existingRoute : existingRoutes) {
            if (existingRoute.getId() == routeId) {
                result.addErrorMessage("route is already in the list", ResultType.INVALID);
                return result;
            }
        }

        result.setpayload(userProfileRepository.addListEntry(listId, routeId));

        if (result.getpayload() == null) {
            result.addErrorMessage("unable to match an id to existing table row", ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<List<Route>> removeListEntry(int listId, int routeId) {
        Result<List<Route>> result = new Result<>();

        if (listId <= 0 || routeId <= 0) {
            result.addErrorMessage("ids must be greater than or equal to 1", ResultType.INVALID);
            return result;
        }

        result.setpayload(userProfileRepository.removeListEntry(listId, routeId));

        if (result.getpayload() == null) {
            result.addErrorMessage("unable to match an id to existing table row", ResultType.NOT_FOUND);
        }

        return result;
    }
}
