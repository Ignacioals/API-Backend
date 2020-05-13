package dao;

import java.awt.image.RescaleOp;
//import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.Query;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;
//import utils.ConnectionUtils;

import dao.UnidadDAO;
import controlador.Controlador;
import entities.DuenioEntity;
import entities.EdificioEntity;
import entities.UnidadEntity;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;
import views.EdificioView;

public class EdificioDAO {
	
	private List<Edificio> edificios; // lo declaro, porque lo estoy declarando todo el siempo sino
	
	public EdificioDAO() {
		this.edificios= new ArrayList<Edificio>();
	}
	
    
	public List<EdificioView> getEdificiosViews(){
		List<EdificioView> edifView= new ArrayList<EdificioView>();
		List<Edificio> edif=new ArrayList<Edificio>();
		if (edificios.isEmpty()) {
			edif=getTodosEdificios();
		}
		for(Edificio i:edif) {
			edifView.add(i.toView()); //el toView esta en modelo.Edificio
		}
		return edifView;
	}
	
	public List<Edificio> getTodosEdificios(){
		//Transaction transaction = null; SE PUEDE SACAR
		List<Edificio> auxRes=new ArrayList<Edificio>();
		try {
    		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction ts = session.beginTransaction();
			ts.begin();
			List<EdificioEntity> results=session.createQuery("from EdificioEntity").list();
	        ts.commit();
	        //session.close();   ME TIRA ERROR COMO QUE YA LA CERRE
	        for(EdificioEntity i:results) {
	        	auxRes.add(toNegocio(i));
	        }
		} catch (Exception e) {
			System.out.println("Error en getTodosEdificios() de EdificioDAO");
			e.printStackTrace();
		}
		return auxRes;
    }
	
	
	static Edificio toNegocio(EdificioEntity entity) {
		Edificio edificio= new Edificio(entity.getCodigo(),entity.getNombre(), entity.getDireccion());
		
		/*List<Unidad> auxUni=new ArrayList<Unidad>();
		for(UnidadEntity i:entity.getUnidades()) {
			Unidad aux=new Unidad(0, null, null, false, null);
			aux=UnidadDAO.toNegocioEdifParam(i, edificio);
			auxUni.add(aux);
		}
		edificio.setUnidades(auxUni);*/
		return edificio;
	}
	
	
	
	static EdificioEntity toEntity(Edificio entity) {
		return new EdificioEntity(entity.getCodigo(),entity.getNombre(), entity.getDireccion());
	}
	
	// -----------------------FUNCIONA PERF----------------------------------
	
	/*
	public Edificio getEdificio(int codigo) {
    	List<Edificio> aux=new ArrayList<Edificio>();
    	aux=getTodosEdificios();
    	Edificio esElQueBusco=null;
    	for(Edificio i:aux) {
    		if(i.getCodigo()==codigo) {
    			esElQueBusco=i;
    		}
    	}
    	
    	UnidadDAO unidadDAO= new UnidadDAO();
    	List<Unidad> unidades= unidadDAO.getUnidades();
    	
    	
    	for(Unidad uni: unidades) {
    			Edificio busca = uni.getEdificio();
    		
    		//unidadDAO.getId(
        		if(busca.getCodigo() == codigo) {
        				
        				
        			esElQueBusco.agregarUnidad(uni);
        				
        				
        			}
    		}
    	agregarPersonasUnidadEdificio(esElQueBusco);

    	return esElQueBusco;
   }
	
	 */
	
	public Edificio getEdificio(int codigo) {

		try {
			Session s=HibernateUtil.getSessionFactory().getCurrentSession();
			s.beginTransaction();
			//System.out.println("se abrio la sesion");
			//ts.begin();
			//System.out.println("empezo la transaccion");
			EdificioEntity edifEntity = (EdificioEntity)s.createSQLQuery("SELECT * FROM edificios WHERE codigo = :edificio_id")
					.addEntity(EdificioEntity.class).setParameter("edificio_id", codigo).uniqueResult();
			//System.out.println("hice la creacion de la query ya");
			List<UnidadEntity> unidadesEntity = edifEntity.getUnidades();
			
			Edificio edificio = toNegocio(edifEntity);
			//System.out.println("tonegocio");
			//System.out.println(edificio);
			
			List<Unidad> auxUni=new ArrayList<Unidad>();
			for(UnidadEntity i:unidadesEntity) {
				Unidad aux=new Unidad(0, null, null, false, null);
				aux=UnidadDAO.toNegocioEdifParam(i, edificio);
				auxUni.add(aux);
			}
			//System.out.println("hice el for");
			edificio.setUnidades(auxUni);
			//System.out.println("setee unidades");
			s.close();
			return edificio;
		} 
		catch(Exception np) {
			System.out.println("No anda getEdificio - EdificioDAO");
		}
		return null;
	}
	
	
	/*public Edificio getEdificio(int codigo) {
		System.out.println("entre");
    	List<Edificio> aux=new ArrayList<Edificio>();
    	aux=getTodosEdificios();
    	System.out.println("hice get todos edif");
    	Edificio esElQueBusco=null;
    	for(Edificio i:aux) {
    		if(i.getCodigo()==codigo) {
    			esElQueBusco=i;
    		}
    	}
    	//System.out.println(esElQueBusco.getNombre());
    	return esElQueBusco;
	}
	 */
	
	public void agregarPersonasUnidadEdificio(Edificio e) {
		
		System.out.println(e.getUnidades().size());
		List<Unidad> unidads = e.getUnidades();
	    for(Unidad unity: unidads) {
	    	buscarDuenio(unity,e);
		}
	}

	public void buscarDuenio(Unidad unity, Edificio e) {
		DuenioDAO duenioDAO = new DuenioDAO();
		UnidadDAO unidadDAO = new UnidadDAO();
    	List<DuenioEntity> dEntities= duenioDAO.getDuenios();
		for(DuenioEntity dEntity: dEntities) {
			UnidadEntity unity2 = dEntity.getUnidad();
			if(unity.getId() == UnidadDAO.toNegocio(unity2).getId())
				e.agregarUnidad(unity);
		}
	}
	
	
	
	
	
	
	
	
	

	
	
	
}
