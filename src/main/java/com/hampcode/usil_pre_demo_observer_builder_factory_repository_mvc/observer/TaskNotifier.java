package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.observer;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Membresia;


public class TaskNotifier implements TaskObserver {
    @Override
    public String onLibroDisponible(Membresia task) {
        return "Tus libros preferidos estan listos para que puedas solicitarlos!";
    }
}