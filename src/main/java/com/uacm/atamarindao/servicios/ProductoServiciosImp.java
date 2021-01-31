package com.uacm.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.uacm.modelo.Producto;
import com.uacm.repositorio.ProductoRepositorio;

@Service
public class ProductoServicios implements ProductoRepositorio {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;

	@Override
	public List<Producto> findAll() {
		return productoRepositorio.findAll();
	}

	@Override
	public List<Producto> findAll(Sort sort) {
		return productoRepositorio.findAll(sort);
	}

	@Override
	public List<Producto> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Producto> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Producto> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Producto> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		productoRepositorio.deleteAllInBatch();
		
	}

	@Override
	public Producto getOne(Long id) {
		return productoRepositorio.getOne(id);
	}

	@Override
	public <S extends Producto> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Producto> List<S> findAll(Example<S> example, Sort sort) {
		return productoRepositorio.findAll(example, sort);
	}

	@Override
	public Page<Producto> findAll(Pageable pageable) {
		return productoRepositorio.findAll(pageable);
	}

	@Override
	public <S extends Producto> S save(S entity) {
		return productoRepositorio.save(entity);
	}

	@Override
	public Optional<Producto> findById(Long id) {
		return productoRepositorio.findById(id);
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
		productoRepositorio.deleteById(id);
	}

	@Override
	public void delete(Producto entity) {
		productoRepositorio.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends Producto> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Producto> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Producto> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Producto> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Producto> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}


}
