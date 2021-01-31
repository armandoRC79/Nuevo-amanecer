package com.uacm.atamarindao.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import lombok.Data;


@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ProductoPedido {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "El stock debe ser mayor que cero")
    private Double cantidad;
    
    private Double  precio;

    private Long productoId;

    @Transient
    private Double subTotal;

    @Transient
    private Producto producto;
    
    public ProductoPedido(){
        this.cantidad = (double) 0;
        this.precio = (double) 0;

    }

    public Double getSubTotal(){
        if (this.precio > 0  && this.cantidad > 0 ){
            return this.cantidad * this.precio;
        }else {
            return (double) 0;
        }
    }

}
