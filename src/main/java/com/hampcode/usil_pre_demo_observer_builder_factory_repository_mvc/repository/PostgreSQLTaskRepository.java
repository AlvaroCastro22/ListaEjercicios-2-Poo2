package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory.DatabaseConnectionFactory;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreSQLTaskRepository extends AbstractDatabaseTaskRepository {

    @Override
    protected Connection getConnection() throws SQLException {
        return DatabaseConnectionFactory.getConnection(DatabaseType.POSTGRESQL);
    }

    @Override
    public int[] getTaskStatusCounts() {
        int[] counts = new int[2];
        String sql = "{call get_task_status_counts()}";

        try (Connection conn = getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                counts[0] = rs.getInt("completed_count");
                counts[1] = rs.getInt("pending_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return counts;
    }

}
