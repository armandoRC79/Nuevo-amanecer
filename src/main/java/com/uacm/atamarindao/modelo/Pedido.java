package com.uacm.atamarindao.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "usuario_id")
    private Long usuarioId;
    
    @NotNull
    private String numberoPedido;
    
    @NotNull
	private Date fecha;
    
    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<ProductoPedido> productos;
	
    private String status;
    
    @Transient
    private Usuario usuario;
    
    public Pedido(){
        productos = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.fecha = new Date();
    }
	
}
