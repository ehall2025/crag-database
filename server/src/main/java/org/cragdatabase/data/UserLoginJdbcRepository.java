package org.cragdatabase.data;

import org.cragdatabase.data.mappers.ListMapper;
import org.cragdatabase.data.mappers.RouteMapper;
import org.cragdatabase.data.mappers.UserMapper;
import org.cragdatabase.models.Route;
import org.cragdatabase.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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

        User user = jdbcClient.sql(query)
                .param(username)
                .query(new UserMapper())
                .optional().orElse(null);

        if (user != null) {
            List<List<Route>> lists = findListsByUserId(user.getId());
            user.setTickList(lists.get(0));
            user.setTodoList(lists.get(1));
        }

        return user;
    }

    @Override
    public List<List<Route>> findListsByUserId (int userId) {
        //fetch list of list_id's associated with a user
        String sql = """
                        select l.id
                        from user u join list l on u.id = l.user_id
                        where u.id = ?;
                        """;
        List<Integer> listIds = jdbcClient.sql(sql)
                .param(userId)
                .query(Integer.class)
                .list();

        //fetch all routes in each list
        sql = """
                select r.id , r.name , r.area_id , r.description , r.start_position
                from route r
                join list_route lr on r.id = lr.route_id
                join list l on l.id = lr.list_id
                where l.id = ?;
                """;

        List<List<Route>> userLists = new ArrayList<>();

        for (int i = 0; i < listIds.size(); i++) {
            userLists.add(jdbcClient.sql(sql)
                    .param(listIds.get(i))
                    .query(new RouteMapper())
                    .list());
        }

        return userLists;
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
