package org.cragdatabase.data;

import org.cragdatabase.models.RouteList;
import org.cragdatabase.models.User;

import java.util.List;

public interface UserLoginRepository {

    //TODO implement
    User findByUsername(String username);

    //TODO
    User createUser(User user);
}
