package ru.iborisov.igshw.users.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.iborisov.igshw.users.models.User;

import java.util.Optional;

@Repository
public class Users {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public Users(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User createUser(final User user) {
        final var keyHolder = new GeneratedKeyHolder();
        final var params = new MapSqlParameterSource();
        params.addValue("first_name", user.getFirstName());
        params.addValue("last_name", user.getLastName());
        params.addValue("username", user.getUsername());
        params.addValue("password", user.getPassword());

        final var sql = """
                insert into
                 users(first_name, last_name, username, password)
                 values(:first_name, :last_name, :username, :password)
                """;
        jdbcTemplate.update(sql, params, keyHolder);

        final var newUserId = (Integer) Optional.ofNullable(keyHolder.getKeys().get("user_id")).orElse(-1L);
        return user.toBuilder().user_id(newUserId).build();
    }
}
