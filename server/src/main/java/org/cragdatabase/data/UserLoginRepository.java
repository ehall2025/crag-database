package org.cragdatabase.data;

import org.cragdatabase.models.User;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserLoginRepository {

    User findByUsername(String username);

    User createUser(User user);
}
