package com.uacm.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.uacm.RicoTamarindoApplication;
import com.uacm.exceps.ExcepcionInventario;
import com.uacm.exceps.ExcepcionPedido;
import com.uacm.exceps.ExcepcionProducto;
import com.uacm.exceps.ExcepcionUsuario;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes= {RicoTamarindoApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class PedidoTest {

	@Test
	public void noDebeAdmitirInventarioNulo() throws ParseException, ExcepcionUsuario {
		Inventario inventario = null;
		Usuario usuario =  new Usuario ("Paco","pez85", "Administrador");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
		
		Throwable exception = assertThrows(ExcepcionPedido.class,
				()->{Pedido pedido = new Pedido (formato.parse("03/12/2020"), inventario, usuario, 30);});
		assertTrue(exception.getMessage().contains("Algún parámetro es nulo o el número de piezas es negativa"));
		
	}
	
	@Test
	public void noDebeAdmitirUsuarioNulo() throws ParseException {
		Inventario inventario = new Inventario();
		Usuario usuario =  null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
		
		Throwable exception = assertThrows(ExcepcionPedido.class,
				()->{Pedido pedido = new Pedido (formato.parse("03/12/2020"), inventario, usuario, 30);});
		assertTrue(exception.getMessage().contains("Algún parámetro es nulo o el número de piezas es negativa"));
		
	}
	
	@Test
	public void noDebeAdmitirCantidadesNegativa() throws ParseException, ExcepcionUsuario {
		Inventario inventario = new Inventario();
		Usuario usuario =  new Usuario ("Paco","pez85", "Administrador");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
		
		Throwable exception = assertThrows(ExcepcionPedido.class,
				()->{Pedido pedido = new Pedido (formato.parse("03/12/2020"), inventario, usuario, -30);});
		assertTrue(exception.getMessage().contains(""));//No se que regresa :v
		
	}
	
	@Test
	public void testPedidoValido() throws ParseException, ExcepcionUsuario, 
	ExcepcionProducto, ExcepcionInventario, ExcepcionPedido {
		
		Producto producto = new Producto("Tamacleta","Bolsa con 25 paletas", "tama.jpg", 10.50);
		Inventario inventario = new Inventario(producto, 55);
		Usuario usuario =  new Usuario ("Paco","pez85", "Administrador");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
		Pedido pedido = new Pedido (formato.parse("03/12/2020"), inventario, usuario, 30);
		
		assertEquals("Tamacleta", pedido.getInventario().getProducto().getNombre());
		assertEquals("Paco", pedido.getUsuario().getNombre());
	}
	
	/*  Por ser Java un lenguaje fuertemente tipado no aplica estos casos de prueba comentados
	 * sólo sirven de ejemplo 
	@Test
	public void noDebeAdmitirDistintoInventario() throws ParseException {
		//Inventario inventario = null;
		Usuario usuario =  new Usuario ("Paco","pez85", "Administrador");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
		
		Throwable exception = assertThrows(ExcepcionPedido.class,
				()->{Pedido pedido = new Pedido (formato.parse("03/12/2020"), "ncks", usuario, 30);});
		assertTrue(exception.getMessage().contains(""));//No se que regresa :v
		
	}*/
	
	/*@Test
	public void noDebeAdmitirDistintoEntero() throws ParseException {
		Inventario inventario = new Inventario();
		Usuario usuario =  new Usuario ("Paco","pez85", "Administrador");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
		
		Throwable exception = assertThrows(ExcepcionPedido.class,
				()->{Pedido pedido = new Pedido (formato.parse("03/12/2020"), inventario, usuario, 30.1);});
		assertTrue(exception.getMessage().contains("Algún parámetro es nulo o el número de piezas es negativa"));
		
	}
	
	@Test
	public void DebeCrearPedido() throws ParseException {
		Inventario inventario = new Inventario();
		Usuario usuario =  new Usuario ("Paco","pez85", "Administrador");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
		
		Throwable exception = assertThrows(ExcepcionPedido.class,
				()->{Pedido pedido = new Pedido (formato.parse("03/12/2020"), inventario, usuario, 30);});
		assertTrue(exception.getMessage().contains("Algún parámetro es nulo o el número de piezas es negativa"));
		
	}*/
	

}
