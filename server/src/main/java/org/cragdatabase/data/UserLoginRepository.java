package org.cragdatabase.data;

import org.cragdatabase.models.Route;
import org.cragdatabase.models.RouteList;
import org.cragdatabase.models.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface UserLoginRepository {

    User findByUsername(String username);

    List<RouteList> findListsByUserId(int userId);

    User createUser(User user);

    boolean registerAdminAccount(int userId);
}
