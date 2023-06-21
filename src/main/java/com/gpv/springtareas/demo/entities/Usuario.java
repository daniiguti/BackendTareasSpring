package com.gpv.springtareas.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable{
    
    //Campos de la bdd
    @Id
    private String idUsuario;
    private String password;
    private String email;
    private String nombre;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Tarea> listaTareas = new ArrayList<>();
    
    //Constructores
    public Usuario(){
        
    }
    public Usuario(String idUsuario, String password, String email, String nombre) {
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
    public void addTarea(Tarea tarea){
        tarea.setUsuario(this);
        this.listaTareas.add(tarea);
    }
}
