package com.gpv.springtareas.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gpv.springtareas.demo.entities.Tarea;
import com.gpv.springtareas.demo.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDTO implements Serializable{
    
    //Campos de la bdd
    private String idUsuario;
    private String password;
    private String email;
    private String nombre;
    @JsonIgnore
    private List<Tarea> listaTareas = new ArrayList<>();
    
    //Constructores
    public UsuarioDTO(){
        
    }
    public UsuarioDTO(String idUsuario, String password, String email, String nombre) {
        this.idUsuario = idUsuario;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
    }
    
    //Getters y setters
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 
    public List<Tarea> getListaTareas() {
        return listaTareas;
    }
    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }
    
    //Metodos auxiliares
    public static UsuarioDTO geUsuariotDtoFromUsuario(Usuario usuario){
        UsuarioDTO us = new UsuarioDTO(usuario.getIdUsuario(), usuario.getPassword(), usuario.getEmail(), usuario.getNombre());
        return us;
    }
    public static Usuario geUsuarioFromUsuarioDto(UsuarioDTO usuario){
        Usuario us = new Usuario(usuario.getIdUsuario(), usuario.getPassword(), usuario.getEmail(), usuario.getNombre());
        return us;
    }
    public UsuarioDTO getUsuarioDtoAndTareasDtoFromUsuario(Usuario usuario){
        //deberemos de traernos un usuario con sus tareas, y convertirlas a dto
        UsuarioDTO us = new UsuarioDTO(usuario.getIdUsuario(), usuario.getPassword(), usuario.getEmail(), usuario.getNombre());
        return us;
    }
}    