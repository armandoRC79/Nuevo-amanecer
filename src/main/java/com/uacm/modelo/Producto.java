package com.uacm.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.uacm.exceps.ExcepcionProducto;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Producto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
    @Size(max = 100)
	private String nombre;
	@NotNull
    @Size(max = 200)
	private String descripcion;
	@NotNull
	private String imagen;
    @NotNull
    private double precio;
    
    public Producto() {
    }

	public Producto(@NotNull @Size(max = 100) String nombre, 
			@NotNull @Size(max = 200) String descripcion,
			@NotNull String imagen, @NotNull double precio) throws ExcepcionProducto {
			checkParametros(nombre, descripcion, imagen, precio);
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.imagen = imagen;
			this.precio = precio;

	}
	
	private void checkParametros(String nombre, String descripcion, 
			String imagen, double precio) throws ExcepcionProducto {
		
		if(nombre == null || descripcion == null || imagen == null || precio < 0.0 )
			throw new ExcepcionProducto("Existe un parámetro nulo o el precio"
					+ " es una cantidad negativa");
	}

}
