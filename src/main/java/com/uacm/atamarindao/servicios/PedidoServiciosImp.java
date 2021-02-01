package com.uacm.atamarindao.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uacm.atamarindao.modelo.Pedido;
import com.uacm.atamarindao.modelo.Producto;
import com.uacm.atamarindao.modelo.ProductoPedido;
import com.uacm.atamarindao.modelo.Usuario;
import com.uacm.atamarindao.repositorio.PedidoRepositorio;

@Service
public class PedidoServiciosImp implements PedidoServicios{
	
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	
	@Autowired
	private ProductoServicios productoServicios;
	
	@Autowired
	private UsuarioServicios usuarioServicios;
	

	@Override
	public List<Pedido> findAll() {
		return pedidoRepositorio.findAll();
	}
	

    @Override
    public Pedido save(Pedido pedido) {
    	
        Pedido pedidoDB = pedidoRepositorio.findByNumberoPedido(pedido.getNumberoPedido());
        if (pedidoDB !=null){
            return  pedidoDB;
        }
        pedido.setStatus("CREATED");
        pedidoDB = pedidoRepositorio.save(pedido);
        pedidoDB.getProductos().forEach( invoiceItem -> {
            productoServicios.updateStock( invoiceItem.getId(), invoiceItem.getCantidad() * -1);
        });

        return pedidoDB;
    }


	@Override
	public Pedido findById(Long id) {
        Pedido pedido = pedidoRepositorio.findById(id).orElse(null);
        if (pedido != null){
            Usuario usuario = usuarioServicios.findById(pedido.getUsuarioId()).get();
            pedido.setUsuario(usuario);
            List<ProductoPedido> listaProducto = pedido.getProductos().stream().map(productoPedido -> {
                Producto producto = productoServicios.findById(productoPedido.getId()).get();
                productoPedido.setProducto(producto);
                return productoPedido;
            }).collect(Collectors.toList());
            pedido.setProductos(listaProducto);
        }
        return pedido ;
	}


	@Override
	public Pedido update(Pedido pedido) {
        Pedido pedidoDB = findById(pedido.getId());
        if (pedidoDB == null){
            return  null;
        }
        pedidoDB.setUsuarioId(pedido.getUsuarioId());
        pedidoDB.setNumberoPedido(pedido.getNumberoPedido());
        pedidoDB.getProductos().clear();
        pedidoDB.setProductos(pedido.getProductos());
        return pedidoRepositorio.save(pedidoDB);
	}


	@Override
	public Pedido delete(Pedido pedido) {
        Pedido pedidoDB = findById(pedido.getId());
        if (pedidoDB == null){
            return  null;
        }
        pedidoDB.setStatus("DELETED");
        return pedidoRepositorio.save(pedidoDB);
	} 
	
	public List<Pedido> findAllByUser(Long id){
		List<Pedido> pedidosUsuario = new ArrayList<>();
		List<Pedido> pedidos = pedidoRepositorio.findByUsuarioId(id);
		
		if(!pedidos.isEmpty()) {
			pedidosUsuario = pedidos;
		}
		
		return pedidosUsuario;
	}
}
