/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model;

import java.time.LocalDateTime;

/**
 *
 * @author alvar
 */
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Producto {
    private int id;
    private String nombre;
    private double costo_unitario;
    private String categoria;
    private LocalDateTime fecha_ingreso;
    private LocalDateTime fecha_vencimiento;
    private double cantidad;
    private String donador;
    private Estado_producto estado_producto;
    
    public enum Estado_producto{
    HABILITADO,VENCIDO
    }
            
            
}
