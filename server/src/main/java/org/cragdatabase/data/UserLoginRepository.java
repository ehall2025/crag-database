package org.cragdatabase.data;

import org.cragdatabase.models.User;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserLoginRepository {

    User findByUsername(String username) throws DataAccessException;

    User createUser(User user);
}
