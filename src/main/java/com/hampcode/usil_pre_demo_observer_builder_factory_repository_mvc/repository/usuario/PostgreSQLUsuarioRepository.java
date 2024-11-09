/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.usuario;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory.DatabaseConnectionFactory;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.AbstractDatabaseTaskRepository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alvar
 */
public class PostgreSQLUsuarioRepository extends AbstractDatabaseUsuarioRepository {
    @Override
    protected Connection getConnection() throws SQLException {
        return DatabaseConnectionFactory.getConnection(DatabaseType.POSTGRESQL);
    }

    
}
