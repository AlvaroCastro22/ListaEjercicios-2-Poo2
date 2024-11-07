package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.service;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory.TaskRepositoryFactory;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Membresia;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Membresia.Estado_membresia;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.observer.TaskNotifier;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.TaskRepository;

import java.util.List;

public class TaskService {

    private final TaskRepository repository;
    private final TaskNotifier notifier;

    public TaskService(DatabaseType dbType) {
        this.repository = TaskRepositoryFactory.getRepository(dbType);
        this.notifier = new TaskNotifier();
    }

    public void addTask(Membresia task) {
        repository.addTask(task);
    }

    public List<Membresia> getAllTasks() {
        return repository.getAllTasks();
    }

    public String completeTask(Membresia task) {
        task.setEstado_membresia(Estado_membresia.ACTIVA);
        repository.updateTask(task);
        return notifier.onTaskCompleted(task);
    }

    public int[] getTaskStatusCounts() {
        return repository.getTaskStatusCounts();
    }
    public void deleteTask(int id){
        repository.deleteTask(id);
    }
}
