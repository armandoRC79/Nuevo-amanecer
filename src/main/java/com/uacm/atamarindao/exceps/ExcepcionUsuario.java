package com.uacm.exceps;

public class ExcepcionUsuario extends Exception{

	private static final long serialVersionUID = 1L;

	public ExcepcionUsuario(String mensaje){
		super(mensaje);
	}

}
