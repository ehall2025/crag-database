package org.cragdatabase.data;

import org.cragdatabase.models.User;

public interface UserLoginRepository {

    User findByUsername(String username);
}
