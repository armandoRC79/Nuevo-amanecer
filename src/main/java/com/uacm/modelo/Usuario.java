package com.uacm.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.sun.istack.NotNull;

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
	
	public Usuario(String nombre, String password, String rol) {
		this.nombre = nombre;
		this.password = password;
		this.rol = rol;
	}

}
