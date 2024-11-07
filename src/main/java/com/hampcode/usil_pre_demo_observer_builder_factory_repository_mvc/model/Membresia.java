package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Membresia {
    private int id;
    private String nombre_usuario;
    private String tipo_membresia;
    private int numero_libros_permitidos;
    private String lista_favoritos;
    private Estado_membresia estado_membresia;
    public enum Estado_membresia {
        ACTIVA,
        INACTIVA
    }
}