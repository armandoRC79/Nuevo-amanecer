package com.uacm.atamarindao.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uacm.atamarindao.modelo.Usuario;
import com.uacm.atamarindao.repositorio.UsuarioRepositorio;

//@Slf4j
@Service
public class UsuarioServiciosImp implements UsuarioServicios{
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public List<Usuario> findAll() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioRepositorio.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		usuario.setStatus("CREADO");
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public Usuario update(Usuario usuario) {
	
        Usuario usuarioDB = findById(usuario.getId()).get();
        Usuario uRespuesta = null;
       
        if (usuarioDB != null){
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setPassword(usuario.getPassword());
            usuarioDB.setRol(usuario.getRol());
            uRespuesta = usuarioDB;
        }

        return  usuarioRepositorio.save(uRespuesta);
              
	}

	@Override
	public Usuario delete(Long id) {
        Usuario usuarioDB = findById(id).get();
        Usuario uRespuesta = null;
        
        if (usuarioDB != null){
        	usuarioDB.setStatus("DELETED");
        	uRespuesta = usuarioDB;
        }

        return usuarioRepositorio.save(uRespuesta);
	}

}
