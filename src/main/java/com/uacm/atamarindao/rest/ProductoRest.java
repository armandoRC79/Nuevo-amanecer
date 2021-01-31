package com.uacm.atamarindao.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.uacm.atamarindao.modelo.MensajeError;
import com.uacm.atamarindao.modelo.Producto;
import com.uacm.atamarindao.servicios.ProductoServiciosImp;

@RestController
@RequestMapping ("/productos")
public class ProductoRest {
	
	@Autowired
	private ProductoServiciosImp productoServicios;
	/*
	@Autowired
	private ProductoRepositorio productoRepositorio;
	*/
	@GetMapping
	private ResponseEntity<List<Producto>> getAllProductos() {
		List<Producto> productos = productoServicios.findAll();
		ResponseEntity<List<Producto>> respuesta = ResponseEntity.noContent().build();
		
		if(!productos.isEmpty())
			respuesta = ResponseEntity.ok(productos);
		
		return respuesta;
	}
	
    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable("id") Long id) {
        Producto producto =  productoServicios.findById(id).get();
        ResponseEntity<Producto> respuesta = ResponseEntity.notFound().build();
        
        if (null!=producto){
            respuesta =  ResponseEntity.ok(producto);
        }
        
        return respuesta;
    }
    
    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto, BindingResult result){
    	if(result.hasErrors()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensaje(result));
    	}
        Producto productoCreado =  productoServicios.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable("id") Long id, @RequestBody Producto producto){
        producto.setId(id);
        Producto productDB =  productoServicios.update(producto);
        ResponseEntity<Producto> respuesta = ResponseEntity.notFound().build();
        if (productDB != null){
            respuesta = ResponseEntity.ok(productDB);
        }
        return respuesta;
    }
	
	
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> deleteProduct(@PathVariable("id") Long id){
        Producto productDelete = productoServicios.delete(id);
        ResponseEntity<Producto> respuesta = ResponseEntity.notFound().build();
        
        if (productDelete != null){
            respuesta = ResponseEntity.ok(productDelete);
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
