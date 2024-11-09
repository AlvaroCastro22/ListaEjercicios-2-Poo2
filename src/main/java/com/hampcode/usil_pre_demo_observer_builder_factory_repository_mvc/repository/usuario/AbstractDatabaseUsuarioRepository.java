/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.repository.usuario;

import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Membresia;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Usuario;
import com.hampcode.usil_pre_demo_observer_builder_factory_repository_mvc.model.Usuario.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvar
 */
public abstract class AbstractDatabaseUsuarioRepository implements UsuarioRepository {
    protected abstract Connection getConnection() throws SQLException;
    @Override
    public void addUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nombre_usuario,apellido_usuario,correo,contraseña,numero,rol) VALUES (?,?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNombre_usuario());
            stmt.setString(2, usuario.getApellido_usuario());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContraseña());
            stmt.setString(5, usuario.getNumero());
            stmt.setObject(6, usuario.getRol().name());
           
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Usuario getUsuario(String correo,String contraseña){
        Usuario usuario=null;
        String sql ="SELECT * FROM Usuarios WHERE correo= '"+correo+"' AND contraseña= '"+contraseña+"' LIMIT 1;";
        try(Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                usuario = Usuario.builder()
                        .id(rs.getInt("id"))
                        .nombre_usuario(rs.getString("nombre_usuario"))
                        .apellido_usuario(rs.getString("apellido_usuario"))
                        .correo(rs.getString("correo"))
                        .contraseña(rs.getString("contraseña"))
                        .numero(rs.getString("numero"))
                        .rol(Rol.valueOf(rs.getString("rol")))
                        .build();
                
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    @Override
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = Usuario.builder()
                        .id(rs.getInt("id"))
                        .nombre_usuario(rs.getString("nombre_usuario"))
                        .apellido_usuario(rs.getString("apellido_usuario"))
                        .correo(rs.getString("correo"))
                        .contraseña(rs.getString("contraseña"))
                        .numero(rs.getString("numero"))
                        .rol(Rol.valueOf(rs.getString("rol")))
                        .build();
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuarios;
        
    }
    
}
