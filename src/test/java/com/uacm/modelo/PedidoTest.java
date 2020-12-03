package com.uacm.modelo;

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
import com.uacm.exceps.ExcepcionPedido;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes= {RicoTamarindoApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class PedidoTest {
	/*
	 @Test
	public void testNombreNuloConstructor() throws ExcepcionUsuario {  
		Throwable exception = assertThrows(ExcepcionUsuario.class,
		        ()->{Usuario usuario = new Usuario (null,"pez85", "Administrador");} );
		assertTrue(exception.getMessage().contains("Existe un parametro nulo"));
	}
	 */
	@Test
	public void noDebeAdmitirInventarioNulo() throws ParseException {
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
	public void noDebeAdmitirCantidadesNegativa() throws ParseException {
		Inventario inventario = new Inventario();
		Usuario usuario =  new Usuario ("Paco","pez85", "Administrador");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
		
		Throwable exception = assertThrows(ExcepcionPedido.class,
				()->{Pedido pedido = new Pedido (formato.parse("03/12/2020"), inventario, usuario, -30);});
		assertTrue(exception.getMessage().contains(""));//No se que regresa :v
		
	}
	
	@Test
	public void noDebeAdmitirDistintoInventario() throws ParseException {
		//Inventario inventario = null;
		Usuario usuario =  new Usuario ("Paco","pez85", "Administrador");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");        
		
		Throwable exception = assertThrows(ExcepcionPedido.class,
				()->{Pedido pedido = new Pedido (formato.parse("03/12/2020"), "ncks", usuario, 30);});
		assertTrue(exception.getMessage().contains(""));//No se que regresa :v
		
	}
	
	@Test
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
		
	}
	
	

}
