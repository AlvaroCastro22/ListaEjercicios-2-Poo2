/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.service;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory.producto.ProductoRepositoryFactory;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Producto;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.producto.ProductoRepository;
import java.util.List;

/**
 *
 * @author alvar
 */
public class ProductoService {
    private final ProductoRepository repository;
    public ProductoService(DatabaseType dbType){
        this.repository=ProductoRepositoryFactory.getRepository(dbType);
    }
    public void addProduct(Producto producto){
        repository.addProduct(producto);
    }
    public List<Producto> getAllProducto(){
    return repository.getAllProductos();
    }
    
}
