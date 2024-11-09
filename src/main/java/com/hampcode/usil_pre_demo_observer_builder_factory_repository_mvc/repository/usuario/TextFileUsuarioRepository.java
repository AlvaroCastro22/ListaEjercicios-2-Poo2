/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.usuario;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Usuario;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Usuario.Rol;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvar
 */
public class TextFileUsuarioRepository implements UsuarioRepository {
    private final String fileName = "usuarios.txt";
    private int currentId = 1;
    public TextFileUsuarioRepository() {
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
                if (parts.length == 7) {
                    int id = Integer.parseInt(parts[0]);
                    currentId = Math.max(currentId, id + 1); // Incrementa el ID automáticamente
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addUsuario(Usuario usuario) {
        usuario.setId(currentId++);
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(usuario.getId() + "," + usuario.getNombre_usuario() + "," + usuario.getApellido_usuario() + "," + usuario.getCorreo()+","+usuario.getContraseña() + "," + usuario.getNumero() + "," + usuario.getRol().name() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String apellido = parts[2];
                    String correo = parts[3];
                    String contraseña = parts[4];
                    String numero = parts[5];
                    String rol = parts[6];

                    Usuario usuario = Usuario.builder()
                            .id(id)
                            .nombre_usuario(name)
                            .apellido_usuario(apellido)
                            .correo(correo)
                            .contraseña(contraseña)
                            .numero(numero)
                            .rol(Rol.valueOf(rol))
                            
                            .build();
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
        
    }

    
    @Override
    public Usuario getUsuario(String correo_required, String contraseña_required) {
        Usuario usuarioFound = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String apellido = parts[2];
                    String correo = parts[3];
                    String contraseña = parts[4];
                    String numero = parts[5];
                    String rol = parts[6];

                    if(correo.equals(correo_required) && contraseña.equals(contraseña_required))
                    {
                        usuarioFound = Usuario.builder()
                            .id(id)
                            .nombre_usuario(name)
                            .apellido_usuario(apellido)
                            .correo(correo)
                            .contraseña(contraseña)
                            .numero(numero)
                            .rol(Rol.valueOf(rol))
                            
                            .build();
                    }
                    
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioFound;
    }
    
}
