package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
    private int id;
    private String name;
    private boolean isCompleted;
    private String descripcion;
    private Tipo tipo;
    public enum Tipo {
        personal,
        laboral
    }
}