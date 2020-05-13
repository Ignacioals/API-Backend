package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dao.EdificioDAO;
import dao.UnidadDAO;
import dao.PersonaDAO;
import dao.ReclamoDAO;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import modelo.Edificio;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import views.EdificioView;
import views.Estado;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

public class Controlador {

	private static Controlador instancia;
	private static EdificioDAO edificioDAO;
	private static PersonaDAO personaDAO;
	private static UnidadDAO unidadDAO;
	private static ReclamoDAO reclamoDAO;
	
	private Controlador() { 
		Controlador.edificioDAO=new EdificioDAO();
		Controlador.personaDAO = new  PersonaDAO();
		Controlador.unidadDAO = new UnidadDAO();
		Controlador.reclamoDAO = new ReclamoDAO();
	}
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	
	public List<EdificioView> getEdificios(){
		return edificioDAO.getEdificiosViews();//cambie esto
	}
	
	//-------------------FUNCA----------------------
	
	public List<UnidadView> getUnidadesPorEdificio(int codigo) throws EdificioException{
		List<UnidadView> resultado = new ArrayList<UnidadView>();
		Edificio edificio = buscarEdificio(codigo);
		List<Unidad> unidades = edificio.getUnidades();
		for(Unidad unidad : unidades)
			resultado.add(unidad.toView());
		
		return resultado;
	}
	
	//-------------------FUNCA----------------------
	//NAcHO
	public List<PersonaView> habilitadosPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habilitados = edificio.habilitados();
		
		System.out.println(habilitados.size());
		for(Persona persona : habilitados)
			resultado.add(persona.toView());
		return resultado;
	}
	
	//-------------------FUNCA----------------------
	//MICA
	public List<PersonaView> dueniosPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		
		Set<Persona> duenios = edificio.duenios();
		System.out.println(duenios.size());
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		//System.out.println(resultado);
		return resultado;
	}

	//-------------------FUNCA----------------------
	
	public List<PersonaView> habitantesPorEdificio(int codigo) throws EdificioException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Edificio edificio = buscarEdificio(codigo);
		
		Set<Persona> habitantes = edificio.habitantes();//cambie esto antes estaba .duenios()!
		System.out.println(edificio.toView());
		System.out.println(habitantes.size());
		for(Persona persona : habitantes) {
			resultado.add(persona.toView());
		}
		return resultado;
	}
	
	//-------------------FUNCA----------------------
	
	public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> duenios = unidad.getDuenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}
	
	//-------------------FUNCA----------------------
	
	public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
		List<PersonaView> resultado = new ArrayList<PersonaView>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> inquilinos = unidad.getInquilinos();
		for(Persona persona : inquilinos)
			resultado.add(persona.toView());
		return resultado;
	}
	
	//TENGO CERO UNIDADES HABITADAS, CERO INQUILINOS
	
	
	//
	public void transferirUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.transferir(persona);
		unidadDAO.save(unidad);
	}

	public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarDuenio(persona);
	}
	
	//alquilarUnidad y agregarInquilinoUnidad estan juntos en el Test --------------------------
	public void alquilarUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		//System.out.println(persona.toView()); devuelve bien
		unidad.alquilar(persona);
	}

	public void agregarInquilinoUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		
		Persona persona = buscarPersona(documento);
		//System.out.println(persona.toView()); devuelve bien
		unidad.agregarInquilino(persona);
		UnidadDAO.save(unidad);
	}

	//-------------------------------------------------------------------------------------------
	
	
	public void liberarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.liberar();
	}
	
	public void habitarUnidad(int codigo, String piso, String numero) throws UnidadException {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.habitar();
	}
	
	
	//save y otros para abajo
	public void agregarPersona(String documento, String nombre) {
		Persona persona = new Persona(documento, nombre);
		persona.save();
	}//hecho Y FUNCIONA
	
	public void eliminarPersona(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		persona.delete();
	}//hecho y funciona
	
	
	//  completado abajo
	public List<ReclamoView> reclamosPorEdificio(int codigo){
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		Edificio edificio = edificioDAO.getEdificio(codigo);
		List<Reclamo> reclamosEd= reclamoDAO.getReclamosPorEdificio(edificio);
		for(Reclamo i:reclamosEd) {
			resultado.add(i.toView());
		}
		return resultado;
	}
	
	//completado abajo
	public List<ReclamoView> reclamosPorUnidad(int codigo, String piso, String numero) {
		List<ReclamoView> res = new ArrayList<ReclamoView>();
		Unidad unidad= unidadDAO.getUnidad(codigo);
		List<Reclamo> reclamosUni=reclamoDAO.getReclamosPorUnidad(unidad);
		for(Reclamo i:reclamosUni) {
			res.add(i.toView());
		}
		return res;
	}
	
	//completado abajo
	public ReclamoView reclamosPorNumero(int numero) {
		ReclamoView resultado = reclamoDAO.getReclamo(numero).toView();
		return resultado;
	}
	
	//hecho
	public List<ReclamoView> reclamosPorPersona(String documento) {
		List<ReclamoView> resultado = new ArrayList<ReclamoView>();
		Persona persona=personaDAO.getPersona(documento);
		List<Reclamo> recPer=reclamoDAO.getReclamosPorPersona(persona);
		for(Reclamo i:recPer) {
			resultado.add(i.toView());
		}
		return resultado;
	}
 
	//done
	public int agregarReclamo(int codigo, String piso, String numero, String documento, String ubicacion, String descripcion) throws EdificioException, UnidadException, PersonaException {
		Edificio edificio = buscarEdificio(codigo);
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		Reclamo reclamo = new Reclamo(persona, edificio, ubicacion, descripcion, unidad);
		reclamo.save();//hecho
		return reclamo.getNumero();
	}
	
	public void agregarImagenAReclamo(int numero, String direccion, String tipo) throws ReclamoException {
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.agregarImagen(direccion, tipo);
	}
	
	public void cambiarEstado(int numero, Estado estado) throws ReclamoException {
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.cambiarEstado(estado);
		reclamo.update(); //hecho
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	
	//CAMBIADO DE ACA PARA ABAJO
	
	private Edificio buscarEdificio(int codigo) throws EdificioException {
		return edificioDAO.getEdificio(codigo);
	}
	
	//pido el piso y el numero al pepe
	private Unidad buscarUnidad(int codigo, String piso, String numero) throws UnidadException{
		return unidadDAO.getUnidad(codigo);
	}	
	
	private Persona buscarPersona(String documento) throws PersonaException {
		return personaDAO.getPersona(documento);
	}
	
	private Reclamo buscarReclamo(int numero) throws ReclamoException {
		return reclamoDAO.getReclamo(numero);
		/*List<Reclamo> totRec = new ReclamoDAO().getReclamos();
		Reclamo aBuscar= new Reclamo(null, null, null, null, null);
		for(Reclamo z:totRec){
			if(z.getNumero()==numero) {
				aBuscar=z;
			}
		}
		return aBuscar;
		*/
	}
}
