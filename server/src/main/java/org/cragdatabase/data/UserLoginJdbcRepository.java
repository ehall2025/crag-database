package org.cragdatabase.data;

import org.cragdatabase.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;

public class UserLoginJdbcRepository implements UserLoginRepository {

    @Autowired
    private final JdbcClient jdbcClient;

    public UserLoginJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
