package com.uacm.exceps;

public class ExcepcionPedido extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExcepcionPedido(String mensaje) {
		super(mensaje);
	}
}
