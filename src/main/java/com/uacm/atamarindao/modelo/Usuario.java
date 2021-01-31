package com.uacm.atamarindao.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor @NoArgsConstructor @Builder
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
	@NotNull
	private String status;

	
}
