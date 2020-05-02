package ec.edu.ups.modelo;

import java.io.Serializable;

public class Telefono implements Serializable{

	private static final long serialVersionUID = 1L;

	private int codigo;
	private String numero, tipo, operadora, fk_cedula;
	
	public Telefono() {}
	
	public Telefono(int codigo, String numero, String tipo, String operadora, String fk_cedula) {
		super();
		this.codigo = codigo;
		this.numero = numero;
		this.tipo = tipo;
		this.operadora = operadora;
		this.fk_cedula = fk_cedula;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getOperadora() {
		return operadora;
	}
	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFk_cedula() {
		return fk_cedula;
	}

	public void setFk_cedula(String fk_cedula) {
		this.fk_cedula = fk_cedula;
	}

	@Override
	public String toString() {
		return "Telefono [codigo=" + codigo + ", numero=" + numero + ", tipo=" + tipo + ", operadora=" + operadora
				+ ", fk_cedula=" + fk_cedula + "]";
	}
	
	
	
}
