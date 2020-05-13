package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import entities.PersonaEntity;
import modelo.Edificio;


@Entity
@Table(name="unidades")
public class UnidadEntity {
	
	@Id
	@Column(name="identificador")
	private int id;
	@Column(name="piso")
	private String piso;
	@Column(name="numero")
	private String numero;
	@Column(name="habitado")
	private boolean habitado;
	//@Column(name="codigoEdificio")
	@ManyToOne
	@JoinColumn(name="codigoEdificio")  // Indica el nombre con el que se deberá crear la columna dentro de la tabla.
	private EdificioEntity edificio;
	@ManyToMany
	 @JoinTable(name="duenios", joinColumns=@JoinColumn(name="identificador"), inverseJoinColumns=@JoinColumn(name="documento"))  
		private List<PersonaEntity> duenios = new ArrayList<PersonaEntity>(); //lo transformo a personaEntity porque son personas
	@ManyToMany
	@JoinTable(name="inquilinos", joinColumns=@JoinColumn(name="identificador"), inverseJoinColumns=@JoinColumn(name="documento"))  
	private List<PersonaEntity> inquilinos= new ArrayList<PersonaEntity>(); //lo transformo a personaEntity porque son persona
	
	public UnidadEntity() {}
	public UnidadEntity(int id, String piso, String numero,boolean habitado, EdificioEntity edificio, List<PersonaEntity>duenios, List<PersonaEntity>inquilinos) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = habitado;
		this.edificio = edificio;
		this.duenios = duenios;
		this.inquilinos = inquilinos;
	}
	
	public int getId() {
		return id;
	}

	public String getPiso() {
		return piso;
	}

	public String getNumero() {
		return numero;
	}

	public EdificioEntity getEdificio() {
		return edificio;
	}
	
	public List<PersonaEntity> getDuenios() {
		return this.duenios;
	}

	public List<PersonaEntity> getInquilinos() {
		return this.inquilinos;
	}
	
	public boolean estaHabitado() {
		return habitado;
	}
	
}
