package com.uacm.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Inventario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn (name="id_producto")
	private Producto producto;
	@NotNull
	private int piezas;
	
	public Inventario() {
	}

	public Inventario(Producto producto, int piezas) {
		this.producto = producto;
		this.piezas = piezas;
	}
	
}
