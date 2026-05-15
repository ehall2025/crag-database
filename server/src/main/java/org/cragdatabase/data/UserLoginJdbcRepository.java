package org.cragdatabase.data;

import org.cragdatabase.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginJdbcRepository implements UserLoginRepository {

    @Autowired
    private final JdbcClient jdbcClient;

    public UserLoginJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public User findByUsername(String username) {

        String query = "SELECT u.id , u.email , u.password FROM User u WHERE u.email = ?";

        return jdbcClient.sql(query)
                .param(username)
                .query(User.class)
                .optional().orElse(null);
    }


    @Override
    public User createUser(User user) {
        return null;
    }
}
