package com.uacm.atamarindao.servicios;

import java.util.List;
import java.util.Optional;

import com.uacm.atamarindao.modelo.Usuario;

public interface UsuarioServicios {
    public List<Usuario> findAll();
    public Optional<Usuario> findById(Long id);
    public Usuario save(Usuario usuario);
    public Usuario update(Usuario usuario);
    public Usuario delete(Long id);
}
