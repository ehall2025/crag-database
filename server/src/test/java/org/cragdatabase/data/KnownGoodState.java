package org.cragdatabase.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
public class KnownGoodState {

    @Autowired
    JdbcClient jdbcClient;

    public void set() {
        jdbcClient.sql("call set_known_good_state();").update();
    }
}
