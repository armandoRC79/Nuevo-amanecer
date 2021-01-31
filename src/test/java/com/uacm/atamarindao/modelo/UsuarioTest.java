package com.uacm.atamarindao.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.uacm.atamarindao.RicoTamarindoApplication;
import com.uacm.atamarindao.exceps.ExcepcionUsuario;

@SpringBootTest(classes= {RicoTamarindoApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Slf4j
public class UsuarioTest {
	
	@Test
	public void testNombreNuloConstructor() throws ExcepcionUsuario {  
		Throwable exception = assertThrows(ExcepcionUsuario.class,
		        ()->{@SuppressWarnings("unused")
				Usuario usuario = new Usuario (1L,null,"pez85", "Administrador", "CREATE");} );
		assertTrue(exception.getMessage().contains("Existe un parametro nulo"));
	}
	
	@Test
	public void testPsswNuloConstructor() throws ExcepcionUsuario {  
		Throwable exception = assertThrows(ExcepcionUsuario.class,
		        ()->{@SuppressWarnings("unused")
				Usuario usuario = new Usuario (1L,"Paco",null, "Administrador", "CREATE");} );
		assertTrue(exception.getMessage().contains("Existe un parametro nulo"));
	}
	/*
	@Test
	public void testRolNuloConstructor() throws ExcepcionUsuario {  
		Throwable exception = assertThrows(ExcepcionUsuario.class,
		        ()->{Usuario usuario = new Usuario (1L,"Paco","pez85", "Administrador", "CREATE"} );
		assertTrue(exception.getMessage().contains("Existe un parametro nulo"));
	}
	*/
	/*   Dado que java es extrictamente tipado, no es posible agregar otro tipo de variable 
	 * es por esta razón que no aplica las pruebas con id:USP004
	@Test
	public void testParaErrorConstructor() throws ExcepcionUsuario {  
		Throwable exception = assertThrows(ExcepcionUsuario.class,
		        ()->{Usuario usuario = new Usuario (5,7.5, 58);} );
		assertTrue(exception.getMessage().contains("Existe un parametro nulo"));
	}*/
	
	@Test
	public void testConstructor() throws ExcepcionUsuario {  
		Usuario usuario = new Usuario(1L,"Paco","pez85", "Administrador", "CREATE");
		assertEquals("Comer", usuario.getNombre());
		assertEquals("pez85", usuario.getPassword());
		assertEquals("Administrador", usuario.getRol());
	}

}
