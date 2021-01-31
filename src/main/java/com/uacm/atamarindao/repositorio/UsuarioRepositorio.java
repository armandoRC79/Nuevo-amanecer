package com.uacm.atamarindao.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacm.atamarindao.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

}
