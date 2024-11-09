/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.usuario;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Usuario;
import java.util.List;

/**
 *
 * @author alvar
 */
public interface UsuarioRepository {
    void addUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    
    
    Usuario getUsuario(String correo,String contraseña);
}
