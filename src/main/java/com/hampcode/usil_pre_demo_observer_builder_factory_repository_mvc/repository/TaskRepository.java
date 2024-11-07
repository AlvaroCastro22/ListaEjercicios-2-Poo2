package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Task;
import java.util.List;

public interface TaskRepository {
    void addTask(Task task);
    List<Task> getAllTasks();
    void updateTask(Task task);
    void deleteTask(int id);
    
    int[] getTaskStatusCounts(); 
}
