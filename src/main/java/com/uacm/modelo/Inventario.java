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
import com.uacm.exceps.ExcepcionInventario;
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
		try {
			checkParametros(producto, piezas);
			this.producto = producto;
			this.piezas = piezas;
		} catch (ExcepcionInventario e) {
			e.printStackTrace();
		}
	}
	
	private void checkParametros(Producto producto, 
			int piezas) throws ExcepcionInventario {
		if(producto == null || piezas < 0)
			throw new ExcepcionInventario("Producto nulo o número de piezas negativo ");		
	}
	
	public boolean upInventario(int piezas) {
		boolean success = false;
		
		if(piezas >= 0) {
			this.piezas = this.piezas + piezas;
			success = true;
		}
		
		return success;
	}
	
	public boolean downInventario(int piezas) {
		boolean success = false;
		
		if(piezas <= this.piezas && piezas > 0) {
			this.piezas = this.piezas - piezas;
			success = true;
		}
			
		return success;
	}
	
}
