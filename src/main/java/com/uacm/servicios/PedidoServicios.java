package com.uacm.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.uacm.modelo.Pedido;
import com.uacm.repositorio.PedidoRepositorio;

@Service
public class PedidoServicios implements PedidoRepositorio{
	
	@Autowired
	PedidoRepositorio pedidoRepositorio;
	

	@Override
	public List<Pedido> findAll() {
		return pedidoRepositorio.findAll();
	}

	@Override
	public List<Pedido> findAll(Sort sort) {
		return pedidoRepositorio.findAll(sort);
	}
	
	public List<Pedido> findAllByUser(Long id){
		List<Pedido> pedidosUsuario = new ArrayList<>();
		List<Pedido> pedidos = pedidoRepositorio.findAll();
		
		for(Pedido pedido:pedidos) {
			if(pedido.getUsuario().getId() == id) {
				pedidosUsuario.add(pedido);
			}	
		}
		
		return pedidosUsuario;
	}
	

	@Override
	public List<Pedido> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Pedido> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Pedido> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Pedido> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pedido getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Pedido> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Pedido> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Pedido> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Pedido> S save(S entity) {
		// TODO Auto-generated method stub
		return pedidoRepositorio.save(entity);
	}

	@Override
	public Optional<Pedido> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Pedido entity) {
		pedidoRepositorio.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends Pedido> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Pedido> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Pedido> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Pedido> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Pedido> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

}
