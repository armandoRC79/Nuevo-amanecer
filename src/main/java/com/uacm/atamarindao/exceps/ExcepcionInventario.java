package com.uacm.exceps;

public class ExcepcionInventario extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ExcepcionInventario(String mensaje){
		super(mensaje);
	}
}
