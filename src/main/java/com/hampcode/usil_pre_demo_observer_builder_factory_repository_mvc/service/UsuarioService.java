/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.service;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.factory.usuario.UsuarioRepositoryFactory;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.DatabaseType;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Usuario;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.usuario.UsuarioRepository;
import java.util.List;

/**
 *
 * @author alvar
 */
public class UsuarioService {
    private final UsuarioRepository repository;
    public UsuarioService(DatabaseType dbType){
        this.repository=UsuarioRepositoryFactory.getRepository(dbType);
    }
    
    public void addUsuario(Usuario usuario){
        repository.addUsuario(usuario);
    
    }
    
    public List<Usuario> getAllUsuario(){
    return repository.getAllUsuarios();
    }
    
    public Usuario getUsuario(String correo,String contraseña){
        return repository.getUsuario(correo, contraseña);
    }
}
