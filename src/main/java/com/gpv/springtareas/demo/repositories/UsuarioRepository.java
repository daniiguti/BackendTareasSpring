package com.gpv.springtareas.demo.repositories;


import com.gpv.springtareas.demo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    @Query("Select distinct u from Usuario u left join fetch u.listaTareas l where u.idUsuario=:idUsuario")
    Usuario usuarioConTareas(String idUsuario);
    
}
