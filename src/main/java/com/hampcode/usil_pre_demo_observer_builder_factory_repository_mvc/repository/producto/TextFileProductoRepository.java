/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.producto;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Producto;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Producto.Estado_producto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvar
 */
public class TextFileProductoRepository implements ProductoRepository {
    private final String fileName = "productos.txt";
    private int currentId = 1;
    public TextFileProductoRepository() {
        ensureFileExists(); 
        initializeIdCounter();
    }
    private void ensureFileExists() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void initializeIdCounter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    int id = Integer.parseInt(parts[0]);
                    currentId = Math.max(currentId, id + 1); // Incrementa el ID automáticamente
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addProduct(Producto producto) {
        producto.setId(currentId++); // Asigna el ID actual y luego lo incrementa

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(producto.getId() + "," + producto.getNombre() + ","+ producto.getCosto_unitario()+ "," + producto.getCategoria() + "," + producto.getFecha_ingreso().toLocalDate()+","+producto.getFecha_vencimiento().toLocalDate()+","+producto.getCantidad()+","+producto.getDonador()+","+producto.getEstado_producto().name() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    Double costo_unitario = Double.parseDouble(parts[2]);
                    String categoria = parts[3];
                    String fecha_ingreso = parts[4];
                    String fecha_vencimiento = parts[5];
                    Double cantidad = Double.parseDouble(parts[6]);
                    String donador = parts[7];
                    String estado_producto = parts[8];
                    LocalDate fecha_ingreso_nuevo = LocalDate.parse(fecha_ingreso, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDate fecha_vencimiento_nuevo = LocalDate.parse(fecha_vencimiento, DateTimeFormatter.ISO_LOCAL_DATE);
                    Producto producto = Producto.builder()
                            .id(id)
                            .nombre(name)
                            .costo_unitario(costo_unitario)
                            .categoria(categoria)
                            .fecha_ingreso(fecha_ingreso_nuevo.atStartOfDay())
                            .fecha_vencimiento(fecha_vencimiento_nuevo.atStartOfDay())
                            .estado_producto(Estado_producto.valueOf(estado_producto))
                            
                            .build();
                    productos.add(producto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productos;
    }

    
    
}
