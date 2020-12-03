package com.uacm.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.uacm.RicoTamarindoApplication;
import com.uacm.exceps.ExcepcionInventario;
import com.uacm.exceps.ExcepcionProducto;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes= {RicoTamarindoApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class InventarioTest {
	
	@Test
	public void testProductoNuloConstructor() {
		Producto producto = null;
		Throwable exception = assertThrows(ExcepcionInventario.class,
		        ()->{Inventario inventario= new Inventario (producto,5);} );
		assertTrue(exception.getMessage().contains("Producto nulo o número de piezas negativo"));
	}
	
	@Test
	public void testPiezasNegativasNuloConstructor() throws ExcepcionProducto {
		Producto producto2 = new Producto("Tamacleta","bote con 22 paletas", "cleta.jpg", 55.0);
		Throwable exception = assertThrows(ExcepcionInventario.class,
		        ()->{Inventario inventario= new Inventario (producto2,-25);} );
		assertTrue(exception.getMessage().contains("Producto nulo o número de piezas negativo"));
	}
	
	/*Esta prueba no se puede realizar debido a que el lenguaje es fuertemente tipado
	@Test
	public void testOtroObjetoNuloConstructor() throws ExcepcionProducto {
		Usuario usuario = new Usuario("May","2545EE89", "Admin");
		Throwable exception = assertThrows(ExcepcionInventario.class,
		        ()->{Inventario inventario= new Inventario (usuario,-25);} );
		assertTrue(exception.getMessage().contains("Producto nulo o número de piezas negativo"));
	}*/
	
	@Test
	public void testConstructor() throws ExcepcionProducto, ExcepcionInventario {
		Producto producto2 = new Producto("Tamacleta","bote con 22 paletas", "cleta.jpg", 55);
        Inventario inventario= new Inventario (producto2, 0);
		assertEquals("Tamacleta", inventario.getProducto().getNombre());
		assertEquals("bote con 22 paletas", inventario.getProducto().getDescripcion());
		assertEquals("cleta.jpg", inventario.getProducto().getImagen());
		assertTrue(55 == inventario.getProducto().getPrecio());
		assertTrue(0 == inventario.getPiezas());
	}
	
	@Test
	public void testUpInventarioValido() throws ExcepcionProducto, ExcepcionInventario {
		Producto producto2 = new Producto("Tamacleta","bote con 22 paletas", "cleta.jpg", 55);
        Inventario inventario = new Inventario (producto2, 55);
		assertTrue(inventario.upInventario(55));
		assertTrue(110 == inventario.getPiezas());
	}
	
	@Test
	public void testUpInventarioInValido() throws ExcepcionProducto, ExcepcionInventario {
		Producto producto2 = new Producto("Tamacleta","bote con 22 paletas", "cleta.jpg", 55);
        Inventario inventario = new Inventario (producto2, 55);
		assertFalse(inventario.upInventario(-55));
		assertTrue(55 == inventario.getPiezas());
	}
	
	/*Reducir el inventario un producto más de la existencia*/
	@Test
	public void testDownInventarioMasProduc() throws ExcepcionProducto, ExcepcionInventario {
		Producto producto2 = new Producto("Tamacleta","bote con 22 paletas", "cleta.jpg", 60);
        Inventario inventario = new Inventario (producto2, 55);
		assertFalse(inventario.downInventario(55+1));
		assertTrue(55 == inventario.getPiezas());
	}
	
	/*Reducir el inventario en cero*/
	@Test
	public void testDownInventarioCero() throws ExcepcionProducto, ExcepcionInventario {
		Producto producto2 = new Producto("Tamacleta","bote con 22 paletas", "cleta.jpg", 61);
        Inventario inventario = new Inventario (producto2, 55);
		assertFalse(inventario.downInventario(0));
		assertTrue(55 == inventario.getPiezas());
	}
	
	@Test
	public void testDownInventarioValido() throws ExcepcionProducto, ExcepcionInventario {
		Producto producto2 = new Producto("Tamacleta","bote con 22 paletas", "cleta.jpg", 61);
        Inventario inventario = new Inventario (producto2, 55);
		assertTrue(inventario.downInventario(55));
		assertTrue(0 == inventario.getPiezas());
	}
}
