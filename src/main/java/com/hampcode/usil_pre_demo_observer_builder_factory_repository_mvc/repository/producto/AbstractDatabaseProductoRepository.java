/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.producto;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Producto;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Producto.Estado_producto;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvar
 */
public abstract class AbstractDatabaseProductoRepository implements ProductoRepository {
    protected abstract Connection getConnection() throws SQLException;
    @Override
    public void addProduct(Producto producto) {
        String sql = "INSERT INTO Productos (nombre,costo_unitario,categoria,fecha_ingreso,fecha_vencimiento,cantidad,donador,estado_producto) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getCosto_unitario());
            stmt.setString(3, producto.getCategoria());
            stmt.setDate(4, java.sql.Date.valueOf(producto.getFecha_ingreso().toLocalDate()));
            stmt.setDate(5, java.sql.Date.valueOf(producto.getFecha_vencimiento().toLocalDate()));
            stmt.setDouble(6, producto.getCantidad());
            stmt.setString(7, producto.getDonador());
            stmt.setObject(8, producto.getEstado_producto().name());
           
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Productos";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = Producto.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombre"))
                        .costo_unitario(rs.getDouble("costo_unitario"))
                        .categoria(rs.getString("categoria"))
                        .fecha_ingreso(rs.getDate("fecha_ingreso").toLocalDate().atStartOfDay())
                        .fecha_vencimiento(rs.getDate("fecha_vencimiento").toLocalDate().atStartOfDay())
                        .cantidad(rs.getDouble("cantidad"))
                        .donador(rs.getString("donador"))
                        .estado_producto(Estado_producto.valueOf(rs.getString("estado_producto")))
                        .build();
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productos;
        
    }
}
