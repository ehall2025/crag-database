package org.cragdatabase.data;

import org.cragdatabase.data.mappers.UserMapper;
import org.cragdatabase.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Repository
public class UserLoginJdbcRepository implements UserLoginRepository {

    @Autowired
    private final JdbcClient jdbcClient;

    public UserLoginJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public User findByUsername(String username) {

        String query = "SELECT u.id , u.email , u.password , u.role FROM User u WHERE u.email = ?";

        return jdbcClient.sql(query)
                .param(username)
                .query(new UserMapper())
                .optional().orElse(null);
    }


    @Override
    public User createUser(User user) {
        final String sql = """
                insert ignore into user (email, password, role)
                values (:email, :password, :role);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcClient.sql(sql)
                .param("email", user.getUsername())
                .param("password", user.getPassword())
                .param("role", user.getRole().toString())
                .update(keyHolder, "id");

        if (rowsAffected == 0) {
            return null;
        }

        user.setId(keyHolder.getKey().intValue());

        return user;
    }
}
