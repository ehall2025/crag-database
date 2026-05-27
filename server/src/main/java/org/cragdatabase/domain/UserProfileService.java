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

    public Result<List<Route>> addOrRemoveListEntry (int listId, int routeId, String action) {
        Result<List<Route>> result = new Result<>();

        if (listId <= 0 || routeId <= 0) {
            result.addErrorMessage("ids must be greater than or equal to 1", ResultType.INVALID);
            return result;
        }

        if (action.equals("add")) {
            result.setpayload(userProfileRepository.addListEntry(listId, routeId));
        } else {
            result.setpayload(userProfileRepository.removeListEntry(listId, routeId));
        }

        if (result.getpayload() == null) {
            result.addErrorMessage("unable to match an id to existing table row", ResultType.NOT_FOUND);
        }

        return result;
    }
}
