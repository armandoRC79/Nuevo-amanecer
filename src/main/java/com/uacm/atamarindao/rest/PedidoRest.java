package com.uacm.atamarindao.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uacm.atamarindao.modelo.Pedido;
import com.uacm.atamarindao.servicios.PedidoServiciosImp;


@RestController
@RequestMapping ("/pedidos")
public class PedidoRest {

	@Autowired
	private PedidoServiciosImp pedidoServicios;

	@GetMapping
	public ResponseEntity<List<Pedido>> getAllPedidos(){
        List<Pedido> pedidos = pedidoServicios.findAll();
        ResponseEntity<List<Pedido>> respuesta = ResponseEntity.noContent().build();
        
        if (!pedidos.isEmpty()) {
            respuesta = ResponseEntity.ok(pedidos);
        }
        
        return  respuesta;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> getPedido(@PathVariable("id") long id){
        Pedido pedido  = pedidoServicios.findById(id);
        ResponseEntity<Pedido> respuesta = ResponseEntity.notFound().build();
        
        if (pedido != null) {
            respuesta = ResponseEntity.ok(pedido);
        }
        
        return respuesta;
	}
	
	@GetMapping ("byUser/{id}")
	public ResponseEntity<List<Pedido>> getAllPedidosByUsuario(@PathVariable ("id") Long idUsuario){
		return ResponseEntity.ok(pedidoServicios.findAllByUser(idUsuario));
	}
	
	@PostMapping
	private ResponseEntity<Pedido> savePedido(@Valid @RequestBody Pedido pedido, BindingResult result){
		
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensaje(result));
        }
        
        Pedido pedidoDB = pedidoServicios.save(pedido);

        return  ResponseEntity.status( HttpStatus.CREATED).body(pedidoDB);
	}
	
    @PutMapping(value = "/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable("id") long id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        Pedido pedidoActual = pedidoServicios.update(pedido);
        ResponseEntity<Pedido> respuesta = ResponseEntity.notFound().build();

        if (pedidoActual != null) {
            respuesta = ResponseEntity.ok(pedidoActual);
        }
        
        return  respuesta;
    }
	
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Pedido> deleteInvoice(@PathVariable("id") long id) {
        Pedido pedido = pedidoServicios.findById(id);
        ResponseEntity<Pedido> respuesta = ResponseEntity.notFound().build();
        
        if (pedido != null) {
        	pedido = pedidoServicios.delete(pedido);
        	respuesta = ResponseEntity.ok(pedido);
        }

        return respuesta;
    }
    
    private String formatoMensaje( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        MensajeError errorMessage = MensajeError.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
	
}
