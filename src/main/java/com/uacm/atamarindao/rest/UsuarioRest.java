package com.uacm.atamarindao.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uacm.atamarindao.modelo.Usuario;
import com.uacm.atamarindao.servicios.UsuarioServiciosImp;

@RestController
@RequestMapping ("/usuarios")
public class UsuarioRest {

	@Autowired
	private UsuarioServiciosImp usuarioServicios;
	
//	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	private ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> usuarios = usuarioServicios.findAll();
		ResponseEntity<List<Usuario>> respuesta = ResponseEntity.noContent().build();
		
		if(!usuarios.isEmpty())
			respuesta = ResponseEntity.ok(usuarios);
		
		return respuesta;
	}
	
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id) {
        Usuario usuario =  usuarioServicios.findById(id).get();
        ResponseEntity<Usuario> respuesta = ResponseEntity.notFound().build();
        if (usuario != null){
            respuesta =  ResponseEntity.ok(usuario);
        }
        return respuesta;
    }
	
  //  @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	private ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario, BindingResult result){
		
    	if(result.hasErrors()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensaje(result));
    	}
        Usuario usuarioCreado =  usuarioServicios.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
		
	}
	
    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        usuario.setId(id);
        Usuario usuarioDB =  usuarioServicios.update(usuario);
        ResponseEntity<Usuario> respuesta = ResponseEntity.notFound().build();
        if (usuarioDB != null){
            respuesta = ResponseEntity.ok(usuarioDB);
        }
        return respuesta;
    }
	
	@DeleteMapping (value = "/{id}")
	private ResponseEntity<Usuario> deleteUsuario(@PathVariable ("id") Long id){
		
        Usuario usuarioDelete = usuarioServicios.delete(id);
        ResponseEntity<Usuario> respuesta = ResponseEntity.notFound().build();
        if (usuarioDelete != null){
            respuesta = ResponseEntity.ok(usuarioDelete);
        }
        return respuesta;
		
	}
	
    private String formatoMensaje( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        MensajeError errorMessage = MensajeError.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
