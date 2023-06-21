package com.gpv.springtareas.demo.repositories;

import com.gpv.springtareas.demo.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer>{

}   
