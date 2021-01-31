package com.uacm.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uacm.modelo.Usuario;
import com.uacm.servicios.UsuarioServicios;

@RestController
@RequestMapping ("/usuarios/")
public class UsuarioRest {

	@Autowired
	private UsuarioServicios usuarioServicios;
	
	@GetMapping
	private ResponseEntity<List<Usuario>> getAllUsuarios() {
		return ResponseEntity.ok(usuarioServicios.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
		try {
			Usuario usuarioGuardado = usuarioServicios.save(usuario);
			return ResponseEntity.created(new URI("/usuarios/"+usuarioGuardado.getId())).body(usuarioGuardado);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping (value = "delete/{id}")
	private ResponseEntity<Boolean> deleteUsuario(@PathVariable ("id") Long id){
		usuarioServicios.deleteById(id);
		return ResponseEntity.ok(!(usuarioServicios.findById(id).isPresent()));
	}
	
}
