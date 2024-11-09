/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.controller;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Usuario;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.service.UsuarioService;
import java.util.List;

/**
 *
 * @author alvar
 */
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService){
    this.usuarioService=usuarioService;
    }
    
    public void addTask(Usuario usuario) {
        usuarioService.addUsuario(usuario);
    }

    public List<Usuario> getAllTasks() {
        return usuarioService.getAllUsuario();
    }
    
    public Usuario getUsuario(String correo,String contraseña){
        return usuarioService.getUsuario(correo, contraseña);
    }
}
