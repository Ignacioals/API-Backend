package modelo;

import java.util.ArrayList;
import java.util.List;

import dao.ReclamoDAO;
import views.Estado;
import views.ImagenView;
import views.ReclamoView;

public class Reclamo {

	private int numero;
	private Persona usuario;
	private Edificio edificio;
	private String ubicacion;
	private String descripcion;
	private Unidad unidad;
	private String estado;
	//private Estado estado;
	private List<Imagen> imagenes;
	
	public Reclamo(Persona usuario, Edificio edificio, String ubicacion, String descripcion, Unidad unidad) {
		this.usuario = usuario;
		this.edificio = edificio;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.unidad = unidad;
		this.estado = "nuevo";
		imagenes = new ArrayList<Imagen>();
	}

	public void agregarImagen(String direccion, String tipo) {
		Imagen imagen = new Imagen(direccion, tipo);
		imagenes.add(imagen);
		imagen.save(numero);
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Persona getUsuario() {
		return usuario;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public String getEstado() {
		return estado;
	}
	
	public List<Imagen> getImagenes(){
		return this.imagenes;
	}
	
	public void cambiarEstado(String estado) {
		this.estado = estado;
	}

	public void save() {
		ReclamoDAO.save(this);
	}
	
	public void update() {
		ReclamoDAO.update(this);
	}
	
	public ReclamoView toView() {
		List<ImagenView> imagenView= new ArrayList<ImagenView>();
		for(Imagen i:this.getImagenes()) {
			imagenView.add(ImagenView.toView());
		}
		return new ReclamoView(this.getNumero(), this.getUsuario().toView(),this.getEdificio().toView(),this.getUbicacion(), this.getDescripcion(),this.getUnidad().toView(), this.getEstado(), imagenView);
	}
}
