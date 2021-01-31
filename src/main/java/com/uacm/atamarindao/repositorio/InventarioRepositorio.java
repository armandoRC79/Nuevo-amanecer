package com.uacm.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacm.modelo.Inventario;

public interface InventarioRepositorio extends JpaRepository<Inventario, Long> {

}
