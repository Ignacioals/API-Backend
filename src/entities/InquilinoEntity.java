package entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="inquilino")
public class InquilinoEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//@Column(name="identificador")
	@ManyToOne
	@JoinColumn(name="identificador")
	private UnidadEntity unidad;
	//@Column(name="documento")
	@ManyToMany
	@JoinColumn(name="documento")
	@Transient
	private PersonaEntity persona;
	
	public InquilinoEntity() { }
	
	public InquilinoEntity(int x, UnidadEntity unidad, PersonaEntity persona) {
		this.id=x;
		this.persona=persona;
		this.unidad=unidad;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
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
