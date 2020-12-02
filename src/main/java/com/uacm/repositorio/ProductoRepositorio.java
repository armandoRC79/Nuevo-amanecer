package com.uacm.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacm.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{

}
