package com.uacm.atamarindao.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.uacm.atamarindao.RicoTamarindoApplication;
import com.uacm.atamarindao.exceps.ExcepcionProducto;

@SpringBootTest(classes= {RicoTamarindoApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Slf4j
public class ProductoTest {
	
	@Test
	public void testNombreNuloConstructor() {
		Throwable exception = assertThrows(ExcepcionProducto.class,
		        ()->{@SuppressWarnings("unused")
				Producto producto = new Producto (1L,null, "Bolsa con 25 paletas", "tama.jpg", 5.0, 10.0, "CREATE");} );
		assertTrue(exception.getMessage().contains("Se requiere el nombre del producto"));
	}
	
	@Test
	public void testDescripcionNuloConstructor() {
		Throwable exception = assertThrows(ExcepcionProducto.class,
		        ()->{@SuppressWarnings("unused")
				Producto producto = new Producto (1L,"Tamacleta",null, "tama.jpg", 5.0, 10.0, "CREATE");} );
		assertTrue(exception.getMessage().contains("Existe un parámetro nulo o el precio es una cantidad negativa"));
	}
	
	@Test
	public void testImagenNuloConstructor() {
		Throwable exception = assertThrows(ExcepcionProducto.class,
		        ()->{@SuppressWarnings("unused")
				Producto producto = new Producto (1L,"Tamacleta","Bolsa con 25 paletas", null, 5.0, 10.0, "CREATE");} );
		assertTrue(exception.getMessage().contains("Existe un parámetro nulo o el precio es una cantidad negativa"));
	}
	
	@Test
	public void testPrecioNegativoConstructor() {
		Throwable exception = assertThrows(ExcepcionProducto.class,
		        ()->{@SuppressWarnings("unused")
				Producto producto = new Producto (1L,"Tamacleta","Bolsa con 25 paletas", "tama.jpg", -0.01, 10.0, "CREATE");} );
		assertTrue(exception.getMessage().contains("Existe un parámetro nulo o el precio es una cantidad negativa"));
	}
	
	@Test
	public void testPrecioCeroConstructor() throws ExcepcionProducto {
		Producto producto = new Producto (1L,"Tamacleta","Bolsa con 25 paletas", "tama.jpg", 0.0, 10.0, "CREATE");
		assertTrue(0.0 == producto.getPrecio());
	}
	
	@Test
	public void testConstructor() throws ExcepcionProducto {
		Producto producto = new Producto (1L,"Tamacleta","Bolsa con 25 paletas", "tama.jpg", 10.50, 100.0, "CREATE");
		assertEquals("Tamacleta", producto.getNombre());
		assertEquals("Bolsa con 25 paletas", producto.getDescripcion());
		assertEquals("tama.jpg", producto.getImagen());
		assertTrue(10.50 == producto.getPrecio());
	}
}
