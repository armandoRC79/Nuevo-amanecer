package com.uacm.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.uacm.exceps.ExcepcionProducto;
import com.uacm.modelo.Producto;
import com.uacm.repositorio.ProductoRepositorio;
import com.uacm.servicios.ProductoServicios;

@RestController
@RequestMapping ("/productos/")
public class ProductoRest {
	
	@Autowired
	private ProductoServicios productoServicio;
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@GetMapping
	private ResponseEntity<List<Producto>> getAllProductos() {
		return ResponseEntity.ok(productoServicio.findAll());
	}
	
	//Este método sirve para guardar y editar ya que la función save guarda el registro si no existe o lo edita si ya está en BD
		@PostMapping (value="/registra-producto")
		public Producto saveProducto(WebRequest request, @RequestParam("nombre") String nombre,
	            @RequestParam("descripcion") String descripcion, @RequestParam("imagen") String imagen
	            , @RequestParam("precio") double precio ) throws ExcepcionProducto{
			
			
	        Producto producto = new Producto(nombre, descripcion, imagen, precio);
	        

			return productoRepositorio.save(producto);
		}
		

		@GetMapping (value="/elimina-producto")
		public String deletePedido(WebRequest request, @RequestParam ("id") Long idProducto){
			productoRepositorio.deleteById(idProducto);
			Optional<Producto> producto = productoRepositorio.findById(idProducto);
			if(producto.isPresent()) {
				return "Ha ocurrido un error";
			} else {
				return "Pedido eliminado correctamente";
			}
		}

}
