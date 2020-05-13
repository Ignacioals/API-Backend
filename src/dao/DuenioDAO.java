package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import entities.DuenioEntity;
import entities.EdificioEntity;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Persona;
import sun.security.action.GetBooleanAction;

public class DuenioDAO {
	
	public List<DuenioEntity> getDuenios() {
		List<DuenioEntity> duenioEntities = new ArrayList<DuenioEntity>();
		try {
    		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction ts = session.beginTransaction();
			ts.begin();
			List<DuenioEntity> results=session.createQuery("from DuenioEntity").list();
	        ts.commit();
	        //session.close();   ME TIRA ERROR COMO QUE YA LA CERRE
	         duenioEntities= results;
		} catch (Exception e) {
			System.out.println("Error en getTodosEdificios() de EdificioDAO");
			e.printStackTrace();
		}
		return duenioEntities;
	}
	
	static Persona toNegocio(DuenioEntity duenioEntity) {
		return new Persona(duenioEntity.getPersona().getDocumento(), duenioEntity.getPersona().getNombre());
	}

	static List<Persona> toNegocio(List<DuenioEntity> dueniosEntity) {
		List<Persona> personas = new ArrayList<Persona>();
	
		for(DuenioEntity duenioEntity:dueniosEntity ) {
			personas.add(PersonaDAO.toNegocio(duenioEntity.getPersona()));
		}
		return personas;
		
	}
	
}
