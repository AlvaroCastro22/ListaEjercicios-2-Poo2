package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.observer;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Task;


public class TaskNotifier implements TaskObserver {
    @Override
    public String onTaskCompleted(Task task) {
        return "La tarea '" + task.getNombre_usuario()+ "' ha sido completada!";
    }
}