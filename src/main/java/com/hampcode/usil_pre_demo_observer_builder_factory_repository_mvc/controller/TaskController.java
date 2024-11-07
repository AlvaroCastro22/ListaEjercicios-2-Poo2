package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.controller;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Membresia;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.service.TaskService;

import java.util.List;

public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public void addTask(Membresia task) {
        taskService.addTask(task);
    }

    public List<Membresia> getAllTasks() {
        return taskService.getAllTasks();
    }

    public String completeTask(Membresia task) {
        return taskService.completeTask(task);
    }

   
    public int[] getTaskStatusCounts() {
        return taskService.getTaskStatusCounts();
    }
    public void deleteTask(int id){
        taskService.deleteTask(id);
    }
}
