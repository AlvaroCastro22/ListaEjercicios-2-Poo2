/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.controller;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Producto;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.service.ProductoService;
import java.util.List;

/**
 *
 * @author alvar
 */
public class ProductoController {
    private final ProductoService productoService;
    public ProductoController(ProductoService productoService){
    this.productoService=productoService;
    }
    
    public void addProducto(Producto producto) {
        productoService.addProduct(producto);
    }

    public List<Producto> getAllProductos() {
        return productoService.getAllProducto();
    }
}
