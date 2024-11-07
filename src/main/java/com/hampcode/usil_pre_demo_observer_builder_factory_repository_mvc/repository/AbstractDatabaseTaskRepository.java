package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDatabaseTaskRepository implements TaskRepository {

    protected abstract Connection getConnection() throws SQLException;
    
    @Override
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (name, isCompleted,descripcion) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getName());
            stmt.setBoolean(2, task.isCompleted());
            stmt.setString(3,task.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Task task = Task.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .isCompleted(rs.getBoolean("isCompleted"))
                        .descripcion(rs.getString("descripcion"))
                        .build();
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tasks;
    }

    @Override
    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET name = ?, isCompleted = ?,descripcion=? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getName());
            stmt.setBoolean(2, task.isCompleted());
            stmt.setString(3,task.getDescripcion());
            stmt.setInt(4, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
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
