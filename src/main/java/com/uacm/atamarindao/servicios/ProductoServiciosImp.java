package com.uacm.atamarindao.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uacm.atamarindao.modelo.Producto;
import com.uacm.atamarindao.repositorio.ProductoRepositorio;

//@Slf4j
@Service
public class ProductoServiciosImp implements ProductoServicios {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;

	@Override
	public List<Producto> findAll() {
		return productoRepositorio.findAll();
	}

	@Override
	public Optional<Producto> findById(Long id) {
		return productoRepositorio.findById(id);
	}

	@Override
	public Producto save(Producto producto) {
		producto.setStatus("EN EXISTENCIA");
		return productoRepositorio.save(producto);
	}

	@Override
	public Producto update(Producto producto) {
		Producto productoDB = findById(producto.getId()).get();
		Producto pRespuesta = null;
		
		if(productoDB != null) {
			productoDB.setNombre(producto.getNombre());
			productoDB.setDescripcion(producto.getDescripcion());
			productoDB.setImagen(producto.getImagen());
			productoDB.setPrecio(producto.getPrecio());
			pRespuesta = productoDB;
		}
		
		return productoRepositorio.save(pRespuesta);
	}

	@Override
	public Producto delete(Long id) {
        Producto productoDB = findById(id).get();
        Producto pRespuesta = null;
        
        if (productoDB != null){
            productoDB.setStatus("DELETED");
        	pRespuesta = productoDB;
        }

        return productoRepositorio.save(pRespuesta);
	}

	@Override
	public Producto updateStock(Long id, Double cantidad) {
        Producto productoDB = findById(id).get();
        Producto pRespuesta = null;
        
        if (productoDB != null){
            Double stock =  productoDB.getStock() + cantidad;
            productoDB.setStock(stock);
            pRespuesta = productoDB;
        }

        return productoRepositorio.save(pRespuesta);
	}
	
}
