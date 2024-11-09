/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory.producto;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import static com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType.MYSQL;
import static com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType.POSTGRESQL;
import static com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType.TEXTFILE;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.producto.MySQLProductoRepository;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.producto.PostgreSQLProductoRepository;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.producto.ProductoRepository;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.producto.TextFileProductoRepository;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.usuario.MySQLUsuarioRepository;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.usuario.PostgreSQLUsuarioRepository;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.usuario.UsuarioRepository;

/**
 *
 * @author alvar
 */
public class ProductoRepositoryFactory {
    public static ProductoRepository getRepository(DatabaseType dbType) {
        return switch (dbType) {
            case MYSQL -> new MySQLProductoRepository();
            case POSTGRESQL -> new PostgreSQLProductoRepository();
            case TEXTFILE -> new TextFileProductoRepository();
        };
    }
}
