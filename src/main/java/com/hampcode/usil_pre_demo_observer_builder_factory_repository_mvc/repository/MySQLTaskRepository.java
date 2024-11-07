package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory.DatabaseConnectionFactory;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLTaskRepository extends AbstractDatabaseTaskRepository {

    @Override
    protected Connection getConnection() throws SQLException {
        return DatabaseConnectionFactory.getConnection(DatabaseType.MYSQL);
    }

    @Override
    public int[] getTaskStatusCounts() {
        int[] counts = new int[2];
        String sql = "{CALL GetTaskStatusCounts(?, ?)}";
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, java.sql.Types.INTEGER);
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);
            stmt.execute();
            counts[0] = stmt.getInt(1);
            counts[1] = stmt.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }
}
