package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="duenios")
public class DuenioEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // para generar el id automatico, de internet
	private int id;
	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad; //esto seria el identificador 
	@OneToOne
	@JoinColumn(name="documento")
	private PersonaEntity persona; //esto haria referencia al documento de la persona
	
	public DuenioEntity() { }
	
	public DuenioEntity( UnidadEntity unidad, PersonaEntity persona) {
		
		this.unidad=unidad;
		this.persona=persona;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public UnidadEntity getUnidad() {
		return unidad;
	}
	
	public void setUnidad(UnidadEntity unidad) {
		this.unidad=unidad;
	}
	
	public PersonaEntity getPersona() {
		return persona;
	}
	
	public void setPersona(PersonaEntity persona) {
		this.persona=persona;
	}
	
}
