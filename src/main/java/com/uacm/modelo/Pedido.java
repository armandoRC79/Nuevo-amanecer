package com.uacm.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.uacm.exceps.ExcepcionPedido;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
	private Date fecha;
    @NotNull
    @ManyToOne
    @JoinColumn (name="id_producto")
	private Inventario inventario;
    @NotNull
    @ManyToOne
	@JoinColumn (name="id_usuario")
	private Usuario usuario;
	private int piezasPedidas;
	
	public Pedido() {
	}

	public Pedido(@NotNull Date fecha, @NotNull Inventario inventario, 
			@NotNull Usuario usuario, int piezasPedidas) {
		try {
			checkParametros(fecha,inventario, usuario, piezasPedidas);
			this.fecha = fecha;
			this.inventario = inventario;
			this.usuario = usuario;
			this.piezasPedidas = piezasPedidas;
		} catch (ExcepcionPedido e) {
			e.printStackTrace();
		}
	}
	
	private void checkParametros(Date fecha, Inventario inventario, 
			Usuario usuario, int piezasPedidas) throws ExcepcionPedido {
		if(fecha == null || inventario == null || usuario == null || piezasPedidas < 0)
			throw new ExcepcionPedido("Algún parámetro es nulo o el número de piezas es negativa");		
	}
}
