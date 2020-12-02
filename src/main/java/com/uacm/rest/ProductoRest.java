package com.uacm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uacm.modelo.Producto;
import com.uacm.servicios.ProductoServicios;

@RestController
@RequestMapping ("/productos/")
public class ProductoRest {
	
	@Autowired
	private ProductoServicios productoServicio;
	
	@GetMapping
	private ResponseEntity<List<Producto>> getAllProductos() {
		return ResponseEntity.ok(productoServicio.findAll());
	}

}
