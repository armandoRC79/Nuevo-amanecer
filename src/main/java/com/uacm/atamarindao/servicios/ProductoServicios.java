package com.uacm.atamarindao.servicios;

import java.util.List;
import java.util.Optional;

import com.uacm.atamarindao.modelo.Producto;

public interface ProductoServicios {
	public List<Producto> findAll();
    public Optional<Producto> findById(Long id);
    public Producto save(Producto producto);
    public Producto update(Producto producto);
    public Producto delete(Long id);
    public Producto updateStock(Long id, Double cantidad);
}
