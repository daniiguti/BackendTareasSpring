package com.gpv.springtareas.demo.services.impl;

import com.gpv.springtareas.demo.entities.Usuario;
import com.gpv.springtareas.demo.repositories.UsuarioRepository;
import com.gpv.springtareas.demo.services.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("usuarioServiceImpl")
@Transactional
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository ur;

    @Override
    public Usuario addUsuario(Usuario usuario) {
        return this.ur.save(usuario);
    }

    @Override
    public Usuario buscarUsuario(String id) {
        Optional<Usuario> user = this.ur.findById(id);
        if(user.isEmpty()){
            return null;
        }else{
            return user.get();
        }
    }

    @Override
    public Usuario buscarUsuarioConTareas(String id) {
        return this.ur.usuarioConTareas(id);
    }

    @Override
    public List<Usuario> encontrarTodos() {
        return this.ur.findAll();
    }

    @Override
    public void eliminarUsuario(String id) {
        this.ur.deleteById(id);
    }
}
