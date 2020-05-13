package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import modelo.Edificio;
import modelo.Imagen;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import views.Estado;

@Entity
@Table(name="reclamos")
public class ReclamoEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idReclamo")
	private int numero;
	
	//HAY ERRORES DE ANOTACION
	//@Column(name="documento")
	@OneToOne
	@JoinColumn(name="documento")  
	private PersonaEntity usuario;
	
	//@Column(name="codigo")
	@OneToOne
	@JoinColumn(name="codigo") 
	private EdificioEntity edificio;
	
	private String ubicacion;
	private String descripcion;
	
	//@Column(name="identificador")
	@OneToOne
	@JoinColumn(name="identificador")  
	private UnidadEntity unidad;
	
	private String estado; // esta en las views, no en negocio
	@OneToMany
	private List<ImagenEntity> imagenes;
	
	public ReclamoEntity() { }
	
	public ReclamoEntity(PersonaEntity usuario, EdificioEntity edificio, String ubicacion, String descripcion, UnidadEntity unidad) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = "nuevo";
		imagenes = new ArrayList<ImagenEntity>();
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
//VERIFICAR ESTO!!
	public PersonaEntity getUsuario() {
		//new Persona(entity.getDocumento(), entity.getNombre());
		return usuario;
		//PersonaEntity entity = new PersonaEntity();
		//return new Persona(entity.getDocumento(), entity.getNombre());
	} //esto aca es Negocio, pero lo tengo que pasar a entity para que no haya problema con las anotaciones (COMO LO HAGO?!)

	public EdificioEntity getEdificio() {
		return edificio;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public UnidadEntity getUnidad() {
		return unidad;
	}

	public String getEstado() {
		return estado;
	}
	
	public List<ImagenEntity> getImagenes(){
		return this.imagenes;
	}
}
