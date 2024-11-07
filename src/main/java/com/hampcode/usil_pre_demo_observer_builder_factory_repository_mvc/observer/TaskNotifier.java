package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.observer;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Membresia;


public class TaskNotifier implements TaskObserver {
    @Override
    public String onTaskCompleted(Membresia task) {
        return "La membresia de '" + task.getNombre_usuario()+ "' ha sido agregada!";
    }
}