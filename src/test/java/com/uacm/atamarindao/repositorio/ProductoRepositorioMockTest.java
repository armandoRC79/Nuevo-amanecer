package com.uacm.atamarindao.repositorio;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.uacm.atamarindao.modelo.Producto;

@DataJpaTest
public class ProductoRepositorioMockTest {
	
	@Autowired
	private ProductoRepositorio productoRespositorio;
	
	@Test
	public void agregaProducto_retornaListaDeProducto() {
		Producto producto = Producto.builder()
				.id(4L)
				.nombre("banderillas")
				.descripcion("banderilla de tamarindo con chile")
				.imagen("banderilla.jpg")
				.status("disponible")
				.precio(Double.parseDouble("6"))
				.stock(Double.parseDouble("200"))
				.status("CREATE")
				.build();
		
		productoRespositorio.save(producto);
		
		List<Producto> productos = productoRespositorio.findAll();
		
		Assertions.assertThat(productos.size()).isEqualTo(4);
	}

}
