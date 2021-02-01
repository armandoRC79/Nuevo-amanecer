package com.uacm.atamarindao.servicios;

import java.util.List;

import com.uacm.atamarindao.modelo.Pedido;

public interface PedidoServicios {
	
    public List<Pedido> findAll();
    public Pedido findById(Long id);
    public Pedido save(Pedido pedido);
    public Pedido update(Pedido pedido);
    public Pedido delete(Pedido pedido);

}
