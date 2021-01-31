package com.uacm.atamarindao.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uacm.atamarindao.modelo.Pedido;
import com.uacm.atamarindao.servicios.PedidoServiciosImp;


@RestController
@RequestMapping ("/pedidos")
public class PedidoRest {

	@Autowired
	private PedidoServiciosImp pedidoServicios;

	@GetMapping
	public ResponseEntity<List<Pedido>> getAllPedidos(){
		return ResponseEntity.ok(pedidoServicios.findAll());
	}
	
	@GetMapping ("{id}")
	public ResponseEntity<List<Pedido>> getAllPedidosByUsuario(@PathVariable ("id") Long idUsuario){
		return ResponseEntity.ok(pedidoServicios.findAllByUser(idUsuario));
	}
	
	@PostMapping
	private ResponseEntity<Pedido> savePedido(@RequestBody Pedido pedido){
		try {
			Pedido pedidoGuardado = pedidoServicios.save(pedido);
			return ResponseEntity.created(new URI("/pedidos/"+pedidoGuardado.getId())).body(pedidoGuardado);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
	/*
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
	}*/
}
