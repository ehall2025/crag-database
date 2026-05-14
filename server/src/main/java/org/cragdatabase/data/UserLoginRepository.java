package org.cragdatabase.data;

import org.cragdatabase.models.User;

public interface UserLoginRepository {

    User findByUsername(String username);

    User createUser(User user);
}
