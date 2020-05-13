package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import entities.PersonaEntity;
import entities.UnidadEntity;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;


public class PersonaDAO {
	
	/*public Persona getPersona(String documento) {
		try {
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		session.beginTransaction();
    		System.out.println("antes de query");
			PersonaEntity perEntity = (PersonaEntity) session.get(PersonaEntity.class, documento);
			Persona persona = toNegocio(perEntity);
			session.close();
			return persona;
		} catch (Exception np) {
			System.out.println("No existe una persona con ese doc");
			return null;
		}
	}*/
	
	public Persona getPersona(String documento) {
    	//Transaction transaction = null; 
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			session.beginTransaction();
			PersonaEntity personaEntity = (PersonaEntity) session.get(PersonaEntity.class, documento);
			session.getTransaction().commit();
			return toNegocio(personaEntity);
		} catch (Exception exception) {
			/*if (transaction != null) {
				transaction.rollback();
			}*/
			System.out.println("No existe ninguna persona con el dni: " + documento);
		}
		return null;
	}
	
	public List<Persona> getPersonas(){
		List<Persona> res=new ArrayList<Persona>();
		Session s= HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<PersonaEntity> personas=s.createQuery("from PersonaEntity").list();
		s.getTransaction().commit();
		s.close();
		for(PersonaEntity p: personas)
			res.add(toNegocio(p));
		return res;	
	}
	
	public static void save(Persona persona) {
		try {
			PersonaEntity aGrabar= toEntity(persona);
			Session sSave= HibernateUtil.getSessionFactory().openSession();
			sSave.beginTransaction();
			sSave.save(aGrabar);
			sSave.getTransaction().commit();
			sSave.close();
		}
		catch(Exception e) {
			System.out.println("No funciona el save - PersonaDAO");
			e.printStackTrace();
		}
	}
	
	public static void delete(Persona persona) {
		try {
			PersonaEntity aBorrar= toEntity(persona);
			Session s= HibernateUtil.getSessionFactory().openSession();
			s.beginTransaction();
			s.delete(aBorrar);
			s.getTransaction().commit();
			s.close();
		}
		catch(Exception e) {
			System.out.println("No funciona el delete - PersonaDAO");
			e.printStackTrace();
		}
	}
	
	
	//paso una Entidad persona(PersonaEntity) a Objeto de Negocio(Persona)
	static Persona toNegocio(PersonaEntity entity) {
		return new Persona(entity.getDocumento(), entity.getNombre());
	}
	
	static List<Persona> toNegocioListas(List<PersonaEntity> perEntity) {
		List<Persona> personas=new ArrayList<Persona>();
		for(PersonaEntity i:perEntity) {
			personas.add(toNegocio(i));
		}
		return personas;
	}
	
	//paso un objeto Persona(Persona) a Entidad persona(PersonaEntity)
	static PersonaEntity toEntity(Persona per) {
		//System.out.println(per.getDocumento());
		//System.out.println(per.getNombre());
		return new PersonaEntity(per.getDocumento(), per.getNombre());
	}
	
	static List<PersonaEntity> toEntityListas(List<Persona> personasEntity) {
		List<PersonaEntity> personasE=new ArrayList<PersonaEntity>();
		for(Persona i:personasEntity) {
			personasE.add(toEntity(i));
		}
		return personasE;
	}
}
