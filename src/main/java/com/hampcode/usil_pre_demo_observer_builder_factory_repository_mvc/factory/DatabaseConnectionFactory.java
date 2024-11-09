package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {

    public static Connection getConnection(DatabaseType dbType) throws SQLException {
        String url;
        String user;
        String password;

        switch (dbType) {
            case MYSQL -> {
                url = "jdbc:mysql://localhost:3306/proyectodb";
                user = "root";  
                password = "12345";
                return DriverManager.getConnection(url, user, password);
            }
            case POSTGRESQL -> {
                url = "jdbc:postgresql://localhost:5432/taskdb";
                user = "postgres";  
                password = "adminadmin";
                return DriverManager.getConnection(url, user, password);
            }
            
            default -> throw new IllegalArgumentException("Tipo de base de datos no soportado");
        }
    }
}
