package com.gpv.springtareas.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAREAS")
public class Tarea implements Serializable{
    
    //Campos de la bdd
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarea;
    private String title;
    private String descripcion;
    private String status;
    private LocalDate dia;
    @Column(name = "ID_USER") 
    private String userId;  
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_USER", insertable = false,  updatable = false)
    private Usuario usuario;
    
    //Constructores
    public Tarea(){
        
    }
    public Tarea(String title, String descripcion, String status, LocalDate dia) {
        this.title = title;
        this.descripcion = descripcion;
        this.status = status;
        this.dia = dia;
    }
    
    //Getters y setters
    public Integer getIdTarea() {
        return idTarea;
    }
    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getDia() {
        return dia;
    }
    public void setDia(LocalDate dia) {
        this.dia = dia;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
