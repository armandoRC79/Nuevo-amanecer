package com.uacm.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import com.uacm.exceps.ExcepcionUsuario;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;
	@NotNull
	private String password;
	@NotNull
	private String rol;
	
	public Usuario() {
	}
	
	public Usuario(@NotNull String nombre, @NotNull String password, @NotNull String rol) {
		try {
			checkParametros(nombre, password, rol);
			this.nombre = nombre;
			this.password = password;
			this.rol = rol;
		} catch (ExcepcionUsuario e) {
			e.printStackTrace();
		}
	}
	
	private void checkParametros(String nombre, String password, String rol) throws ExcepcionUsuario {
		if(nombre == null || password == null || rol == null )
			throw new ExcepcionUsuario("Existe un parametro nulo");
	}
	
}
