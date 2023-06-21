package com.gpv.springtareas.demo.services;

import com.gpv.springtareas.demo.entities.Usuario;
import java.util.List;

public interface UsuarioService {
    
    public List<Usuario> encontrarTodos();
    public Usuario addUsuario(Usuario usuario);
    public Usuario buscarUsuario(String id);
    public Usuario buscarUsuarioConTareas(String id);
    public void eliminarUsuario(String id);
}
