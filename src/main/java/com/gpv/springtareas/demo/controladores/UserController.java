package com.gpv.springtareas.demo.controladores;

import com.gpv.springtareas.demo.dto.UsuarioDTO;
import com.gpv.springtareas.demo.entities.Tarea;
import com.gpv.springtareas.demo.entities.Usuario;
import com.gpv.springtareas.demo.services.TareaService;
import com.gpv.springtareas.demo.services.UsuarioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/usuarios")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    @Qualifier("tareaServiceImpl")
    private TareaService ts;
    
    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService us;
    
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<?> findAllUsers(){
        logger.info("findAllUsers");
 
        List<Usuario> lstAll = us.encontrarTodos();
        List<UsuarioDTO> lstAllDto = lstAll.stream()
                .map(UsuarioDTO::geUsuariotDtoFromUsuario)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lstAllDto);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> findUserById(@PathVariable String id){
        logger.info("findUserById");
        Usuario user =  us.buscarUsuario(id);
        if(user == null){
            Map<String, String> map = new HashMap<>();
            map.put("Error", "Usuario " + id + " no encontrado");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(map);
        }else{
            UsuarioDTO uDto = UsuarioDTO.geUsuariotDtoFromUsuario(user);
            return ResponseEntity.ok(uDto);
        }       
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/contareas")
    public ResponseEntity<?> findUserConTareas(@PathVariable String id){
        Usuario user =  us.buscarUsuarioConTareas(id);
        return ResponseEntity.ok(user);   
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<?> save(@RequestBody UsuarioDTO usuarioDTO){
        logger.info("save");
        Usuario aux = us.buscarUsuario(usuarioDTO.getIdUsuario());
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
            Usuario u = UsuarioDTO.geUsuarioFromUsuarioDto(usuarioDTO);
            u = us.addUsuario(u);
            UsuarioDTO uDto = UsuarioDTO.geUsuariotDtoFromUsuario(u);
            return ResponseEntity.status(HttpStatus.CREATED).body(uDto);
        }
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        Usuario user =  us.buscarUsuario(id);
        if(user == null){
            Map<String, String> map = new HashMap<>();
            map.put("Error", "Usuario " + id + " no encontrado");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(map);
        }else{
            us.eliminarUsuario(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build(); 
        }       
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody UsuarioDTO usuario){
        logger.info("update");
        Usuario user =  us.buscarUsuario(id);
        if(user == null){
            Map<String, String> map = new HashMap<>();
            map.put("Error", "Usuario " + id + " no encontrado");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(map);
        }else{
            user.setEmail(usuario.getEmail());
            user.setPassword(usuario.getPassword());
            user.setNombre(usuario.getNombre());
            user = us.addUsuario(user);
            UsuarioDTO uDto = UsuarioDTO.geUsuariotDtoFromUsuario(user);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(uDto);
        }       
    }
    
    
    //Métodos para operar con las tareas
    @RequestMapping(value = "/tareas/{id}", method = RequestMethod.POST)
    public Tarea postTarea(@PathVariable("id") String idUsuario, @RequestBody Tarea tarea){
        Usuario usuario = us.buscarUsuarioConTareas(idUsuario);
        tarea.setUsuario(usuario);
        usuario.getListaTareas().add(tarea);
        usuario = us.addUsuario(usuario);  
        return usuario.getListaTareas().get(usuario.getListaTareas().size()-1);
    }
    
    @RequestMapping(value = "/tareas/{idUsuario}/{idTarea}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTarea(@PathVariable("idUsuario") String idUsuario, @PathVariable("idTarea") Integer idTarea){
        Usuario usuario = us.buscarUsuarioConTareas(idUsuario);
        boolean eliminado = false;
        for(int i = 0; i < usuario.getListaTareas().size() && eliminado == false; i++){
            if(usuario.getListaTareas().get(i).getIdTarea().equals(idTarea)){
                usuario.getListaTareas().remove(i);
                eliminado = true;
            }
        }
        
        if(eliminado = false){
            Map<String, String> map = new HashMap<>();
            map.put("Error", "Tarea " + idTarea + " no encontrado");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(map);
        }
        else{
            us.addUsuario(usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build(); 
        }  
    }
    
    @RequestMapping(value = "/tareas/{idUsuario}/{idTarea}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTarea(@PathVariable("idUsuario") String idUsuario, @PathVariable("idTarea") Integer idTarea, @RequestBody Tarea tarea){
        logger.info("updateTarea");
        Usuario usuario = us.buscarUsuarioConTareas(idUsuario);
        Tarea aux = null;
        boolean encontrado = false;
        for(int i = 0; i < usuario.getListaTareas().size() && encontrado == false; i++){
            if(usuario.getListaTareas().get(i).getIdTarea().equals(idTarea)){
                aux = usuario.getListaTareas().remove(i);
                encontrado = true;
            }
        }
        
        if(encontrado = false){
            Map<String, String> map = new HashMap<>();
            map.put("Error", "Tarea " + idTarea + " no encontrado");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(map);
        }
        else{
            aux.setTitle(tarea.getTitle());
            aux.setDescripcion(tarea.getDescripcion());
            aux.setDia(tarea.getDia());
            aux.setStatus(tarea.getStatus());
            usuario.addTarea(aux);
            us.addUsuario(usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(aux); 
        } 
    }
}
