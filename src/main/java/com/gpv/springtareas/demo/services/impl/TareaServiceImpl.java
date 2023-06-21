package com.gpv.springtareas.demo.services.impl;

import com.gpv.springtareas.demo.entities.Tarea;
import com.gpv.springtareas.demo.repositories.TareaRepository;
import com.gpv.springtareas.demo.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tareaServiceImpl")
@Transactional
public class TareaServiceImpl implements TareaService{
    
    @Autowired
    private TareaRepository tr;

    @Override
    public void modificarTarea(Integer idTarea, Tarea nuevaTarea) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addTarea(Tarea tarea) {
        tr.save(tarea);
    }

    @Override
    public void deleteTarea(Integer id) {
        this.tr.deleteById(id);
    }
}
