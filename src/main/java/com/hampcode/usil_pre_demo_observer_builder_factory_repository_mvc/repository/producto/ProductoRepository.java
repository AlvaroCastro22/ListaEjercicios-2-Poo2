/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.producto;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Producto;
import java.util.List;

/**
 *
 * @author alvar
 */
public interface ProductoRepository {
    void addProduct(Producto producto);
    List<Producto> getAllProductos();
    
}
