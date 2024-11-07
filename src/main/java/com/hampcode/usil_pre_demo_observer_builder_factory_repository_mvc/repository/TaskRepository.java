package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Membresia;
import java.util.List;

public interface TaskRepository {
    void addTask(Membresia task);
    List<Membresia> getAllTasks();
    void updateTask(Membresia task);
    void deleteTask(int id);
    
    int[] getTaskStatusCounts(); 
}
