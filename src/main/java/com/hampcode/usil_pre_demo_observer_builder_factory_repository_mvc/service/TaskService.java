package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.service;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory.TaskRepositoryFactory;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Task;
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

    public void addTask(Task task) {
        repository.addTask(task);
    }

    public List<Task> getAllTasks() {
        return repository.getAllTasks();
    }

    public String completeTask(Task task) {
        task.setCompleted(true);
        repository.updateTask(task);
        return notifier.onTaskCompleted(task);
    }

    public int[] getTaskStatusCounts() {
        return repository.getTaskStatusCounts();
    }
}
