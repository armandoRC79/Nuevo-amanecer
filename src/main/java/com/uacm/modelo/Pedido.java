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
    @ManyToOne
    @JoinColumn (name="id_producto")
	private Producto producto;
	@ManyToOne
	@JoinColumn (name="id_usuario")
	private Usuario usuario;
	@NotNull
	private int piezasPedidas;
	
	public Pedido() {
	}
	
	public Pedido(@NotNull Date fecha, Producto producto, Usuario usuario, @NotNull int piezasPedidas) {
		this.fecha = fecha;
		this.producto = producto;
		this.usuario = usuario;
		this.piezasPedidas = piezasPedidas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getPiezasPedidas() {
		return piezasPedidas;
	}
	public void setPiezasPedisas(int piezasPedidas) {
		this.piezasPedidas = piezasPedidas;
	}
	
}
