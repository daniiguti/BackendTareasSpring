package com.gpv.springtareas.demo.services;

import com.gpv.springtareas.demo.entities.Tarea;

public interface TareaService {
    
    public void modificarTarea(Integer idTarea, Tarea nuevaTarea);
    public void addTarea(Tarea tarea);
    public void deleteTarea(Integer id);
}
