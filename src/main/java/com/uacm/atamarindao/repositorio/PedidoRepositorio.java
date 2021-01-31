package com.uacm.atamarindao.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacm.atamarindao.modelo.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{
	
    public List<Pedido> findByUsuarioId(Long usuarioId );
    public Pedido findByNumberoPedido(String numberoPedido);

}
