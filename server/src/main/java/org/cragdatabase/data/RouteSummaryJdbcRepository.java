package org.cragdatabase.data;

import org.cragdatabase.data.mappers.RouteSummaryMapper;
import org.cragdatabase.models.RouteSummary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteSummaryJdbcRepository implements RouteSummaryRepository {

    private final JdbcClient jdbcClient;

    public RouteSummaryJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<RouteSummary> findByRouteId(int routeId) {
        String sql = """
                SELECT rs.id, rs.route_id, rs.user_id, rs.difficulty, rs.quality, rs.danger_level
                FROM Route_Summary rs
                WHERE rs.route_id = :route_id;
                """;
        return jdbcClient.sql(sql)
                .param("route_id", routeId)
                .query(new RouteSummaryMapper())
                .list();
    }

    @Override
    public RouteSummary findById(int routeSummaryId) {
        String sql = """
                SELECT rs.id, rs.route_id, rs.user_id, rs.difficulty, rs.quality, rs.danger_level
                FROM Route_Summary rs
                WHERE rs.id = :id;
                """;
        return jdbcClient.sql(sql)
                .param("id", routeSummaryId)
                .query(new RouteSummaryMapper())
                .optional()
                .orElse(null);
    }

    @Override
    public boolean add(RouteSummary routeSummary) {
        String sql = """
                INSERT INTO Route_Summary (route_id, user_id, difficulty, quality, danger_level)
                VALUES (:route_id, :user_id, :difficulty, :quality, :danger_level);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcClient.sql(sql)
                .param("route_id", routeSummary.getRouteId())
                .param("user_id", routeSummary.getUserId())
                .param("difficulty", routeSummary.getDifficultyRating())
                .param("quality", routeSummary.getQualityRating())
                .param("danger_level", routeSummary.getDangerRating())
                .update(keyHolder, "id");

        if (rowsAffected > 0) {
            routeSummary.setRouteSummaryId(keyHolder.getKey().intValue());
        }

        return rowsAffected > 0;
    }

    @Override
    public boolean update(RouteSummary routeSummary) {
        String sql = """
                UPDATE Route_Summary SET
                difficulty = :difficulty,
                quality = :quality,
                danger_level = :danger_level
                WHERE id = :id;
                """;
        return jdbcClient.sql(sql)
                .param("difficulty", routeSummary.getDifficultyRating())
                .param("quality", routeSummary.getQualityRating())
                .param("danger_level", routeSummary.getDangerRating())
                .param("id", routeSummary.getRouteSummaryId())
                .update() > 0;
    }

    @Override
    public boolean deleteById(int routeSummaryId) {
        String sql = """
                DELETE FROM Route_Summary WHERE id = ?;
                """;
        return jdbcClient.sql(sql)
                .param(routeSummaryId)
                .update() > 0;
    }
}
