package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="imagenes")
public class ImagenEntity {
	@Id
	private int numero;
	@Column(name="path")
	private String direccion;
	private String tipo;
	@ManyToOne
	@JoinColumn(name="idReclamo")
	private ReclamoEntity nroReclamo;
	
	public ImagenEntity(){ }
	
	public ImagenEntity(String direccion, String tipo) {
		this.direccion = direccion;
		this.tipo = tipo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
