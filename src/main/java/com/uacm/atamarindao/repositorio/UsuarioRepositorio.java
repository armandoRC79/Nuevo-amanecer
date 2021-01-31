package com.uacm.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacm.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

}
