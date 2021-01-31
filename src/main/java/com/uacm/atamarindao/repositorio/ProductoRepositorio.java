package com.uacm.atamarindao.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacm.atamarindao.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{

}
