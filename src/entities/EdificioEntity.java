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
import javax.persistence.Table;

import org.hibernate.mapping.Array;

import entities.UnidadEntity;
import modelo.Unidad;

@Entity
@Table(name="edificios")
public class EdificioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;
	private String nombre;
	private String direccion;
	
	@OneToMany/*(mappedBy= "edificios")*/
	@JoinColumn(name="codigoEdificio")
	private List<UnidadEntity> unidades=new ArrayList<UnidadEntity>();
	
	public EdificioEntity() { }
	
	public EdificioEntity(int codigo, String nombre, String direccion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.unidades = new ArrayList<UnidadEntity>();
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo=codigo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion=direccion;
	}

	public List<UnidadEntity> getUnidades() {
		return unidades;
	}
	
	public void setUnidades(List<UnidadEntity> unidades) {
		this.unidades = unidades;
	}
	

	
}
