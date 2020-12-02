package com.uacm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uacm.modelo.Pedido;
import com.uacm.servicios.PedidoServicios;

@RestController
@RequestMapping ("/pedidos/")
public class PedidoRest {

	@Autowired
	private PedidoServicios pedidoServicios;
	
	@GetMapping ("{id}")
	public ResponseEntity<List<Pedido>> getAllPedidosByUsuario(@PathVariable ("id") Long idUsuario){
		return ResponseEntity.ok(pedidoServicios.findAllByUser(idUsuario));
	}
	
}
