package com.uacm.atamarindao.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor @NoArgsConstructor @Builder
public class Producto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
    @Size(max = 100)
	@NotEmpty(message = "Se requiere el nombre del producto")
	private String nombre;
	@NotNull
    @Size(max = 200)
	private String descripcion;
	@NotNull
	private String imagen;
    @NotNull
    private double precio;
    
    @Positive(message = "El stock debe ser mayor o igual que cero")
    private Double stock;
    
    @NotEmpty(message = "Sin estatus asignado")
    private String status;

}
