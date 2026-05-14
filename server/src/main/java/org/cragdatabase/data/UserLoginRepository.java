package org.cragdatabase.data;

import org.cragdatabase.models.User;

public interface UserLoginRepository {

    //TODO implement
    User findByUsername(String username);

    //TODO
    User createUser(User user);
}
