package com.uacm.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uacm.modelo.Inventario;
import com.uacm.servicios.InventarioServicios;

@RestController
@RequestMapping ("/inventarios/")
public class InventarioRest {
	
	@Autowired
	private InventarioServicios inventarioServicios;
	
	public ResponseEntity<List<Inventario>> getAllInventarios(){
		return ResponseEntity.ok(inventarioServicios.findAll());
	}
	
	
	
	@PostMapping
	private ResponseEntity<Inventario> saveInventario(@RequestBody Inventario inventario){
		try {
			Inventario inventarioGuardado = inventarioServicios.save(inventario);
			return ResponseEntity.created(new URI("/usuarios/"+inventarioGuardado.getId())).body(inventarioGuardado);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping (value = "delete/{id}")
	private ResponseEntity<Boolean> deleteInventario(@PathVariable ("id") Long id){
		inventarioServicios.deleteById(id);
		return ResponseEntity.ok(!(inventarioServicios.findById(id).isPresent()));
	}
}
