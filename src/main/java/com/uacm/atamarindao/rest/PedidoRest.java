package com.uacm.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.uacm.exceps.ExcepcionPedido;
import com.uacm.modelo.Inventario;
import com.uacm.modelo.Pedido;
import com.uacm.modelo.Usuario;
import com.uacm.repositorio.PedidoRepositorio;
import com.uacm.servicios.PedidoServicios;


@RestController
@RequestMapping ("/pedidos/")
public class PedidoRest {

	@Autowired
	private PedidoServicios pedidoServicios;
	
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	
	@GetMapping ("{id}")
	public ResponseEntity<List<Pedido>> getAllPedidosByUsuario(@PathVariable ("id") Long idUsuario){
		return ResponseEntity.ok(pedidoServicios.findAllByUser(idUsuario));
	}
	
	
	//Este método sirve para guardar y editar ya que la función save guarda el registro si no existe o lo edita si ya está en BD
	@PostMapping (value="/registra-pedido")
	public Pedido savePedidos(WebRequest request, @RequestParam("inventario") Inventario inventario,
            @RequestParam("fecha") String fecha, @RequestParam("usuario") Usuario usuario, @RequestParam("piezas") int piezas ) throws ExcepcionPedido{
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
			fechaDate = formato.parse(fecha);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
        
        Pedido pedido = new Pedido(fechaDate, inventario, usuario, piezas);
        

		return pedidoRepositorio.save(pedido);
	}
	

	@GetMapping (value="/elimina-pedido")
	public String deletePedido(WebRequest request, @RequestParam ("id") Long idPedido){
		pedidoRepositorio.deleteById(idPedido);
		Optional<Pedido> pedido = pedidoRepositorio.findById(idPedido);
		if(pedido.isPresent()) {
			return "Ha ocurrido un error";
		} else {
			return "Pedido eliminado correctamente";
		}
	}
}
