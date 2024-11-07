package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Membresia;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Membresia.Estado_membresia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDatabaseTaskRepository implements TaskRepository {

    protected abstract Connection getConnection() throws SQLException;
    
    @Override
    public void addTask(Membresia task) {
        String sql = "INSERT INTO Membresias (nombre_usuario,tipo_membresia,numero_libros_permitidos,estado_membresia,lista_favoritos) VALUES (?, ?, ?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getNombre_usuario());
            stmt.setString(2, task.getTipo_membresia());
            stmt.setInt(3,task.getNumero_libros_permitidos());
            stmt.setObject(4, task.getEstado_membresia().name());
            stmt.setObject(5, task.getLista_favoritos());
           
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Membresia> getAllTasks() {
        List<Membresia> tasks = new ArrayList<>();
        String sql = "SELECT * FROM Membresias";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Membresia task = Membresia.builder()
                        .id(rs.getInt("id"))
                        .nombre_usuario(rs.getString("nombre_usuario"))
                        .tipo_membresia(rs.getString("tipo_membresia"))
                        .numero_libros_permitidos(rs.getInt("numero_libros_permitidos"))
                        .estado_membresia(Estado_membresia.valueOf(rs.getString("estado_membresia")))
                        .lista_favoritos(rs.getString("lista_favoritos"))
                        .build();
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tasks;
    }

    @Override
    public void updateTask(Membresia task) {
        String sql = "UPDATE Membresias SET nombre_usuario = ?, tipo_membresia = ?,numero_libros_permitidos=?,estado_membresia=?,lista_favoritos=? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getNombre_usuario());
            stmt.setString(2, task.getTipo_membresia());
            stmt.setInt(3,task.getNumero_libros_permitidos());
            stmt.setObject(4, task.getEstado_membresia().name());
            stmt.setObject(5, task.getLista_favoritos());
            stmt.setInt(6, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks2 WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract int[] getTaskStatusCounts();
}
