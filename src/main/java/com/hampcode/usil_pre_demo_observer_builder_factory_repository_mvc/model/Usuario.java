/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model;

/**
 *
 * @author alvar
 */
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {
    private int id;
    private String nombre_usuario;
    private String apellido_usuario;
    private String correo;
    private String contraseña;
    private String numero;
    private Rol rol;
    
    public enum Rol{
        ADMINISTRADOR,VOLUNTARIO
    }
}
