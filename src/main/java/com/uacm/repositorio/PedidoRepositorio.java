package com.uacm.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacm.modelo.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{

}
