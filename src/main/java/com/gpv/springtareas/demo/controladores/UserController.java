package com.gpv.springtareas.demo.controladores;

import com.gpv.springtareas.demo.dto.UsuarioDTO;
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
    
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tareas")
    public ResponseEntity<?> findTareasDeUnUser(@PathVariable String id){
        Usuario user =  us.buscarUsuarioConTareas(id);
        return ResponseEntity.ok(user.getListaTareas());   
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
    
}
