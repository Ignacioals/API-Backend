package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.classic.Session;

import dao.EdificioDAO;
import dao.PersonaDAO;
import dao.UnidadDAO;
import entities.PersonaEntity;
import entities.ReclamoEntity;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;

public class ReclamoDAO {
	
	public static Reclamo getReclamo(int numero) {
		Session s= HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			s.beginTransaction();
			ReclamoEntity reclamoEntity= (ReclamoEntity) s.get(ReclamoEntity.class, numero);
			Reclamo rec = toNegocio(reclamoEntity);
			s.getTransaction().commit();
			
			return rec;
		}
		catch (Exception e) {
			System.out.println("error en getReclamo");
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		return null;
	}
	
	public List<Reclamo> getReclamos(){
		List<Reclamo> res=new ArrayList<Reclamo>();
		Session s= HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<ReclamoEntity> reclamos=s.createQuery("from ReclamoEntity").list();
		s.getTransaction().commit();
		s.close();
		for(ReclamoEntity rec: reclamos)
			res.add(toNegocio(rec));
		return res;	
	}
	
	public static void save(Reclamo reclamo) {
		try {
			System.out.println(reclamo.getEstado());
			ReclamoEntity aGrabar= toEntity(reclamo);
			Session s= HibernateUtil.getSessionFactory().openSession();
			s.beginTransaction();
			s.save(aGrabar);
			s.getTransaction().commit();
			s.close();
		}
		catch(Exception e) {
			System.out.println("No funciona el save - ReclamoDAO");
			e.printStackTrace();
		}
	}
	
	
	public static void delete(Reclamo reclamo) {
		try {
			ReclamoEntity aBorrar= toEntity(reclamo);
			Session s= HibernateUtil.getSessionFactory().openSession();
			s.beginTransaction();
			s.delete(aBorrar);
			s.getTransaction().commit();
			s.close();
		}
		catch(Exception e) {
			System.out.println("No funciona el delete - ReclamoDAO");
			e.printStackTrace();
		}
	}
	
	public static void update(Reclamo reclamo) {
		try {
			ReclamoEntity aGrabar= toEntity(reclamo);
			Session s= HibernateUtil.getSessionFactory().openSession();
			s.beginTransaction();
			s.saveOrUpdate(aGrabar);
			s.getTransaction().commit();
			s.close();
		}
		catch(Exception e) {
			System.out.println("No funciona el update - ReclamoDAO");
			e.printStackTrace();
		}
	}
	
	
	public List<Reclamo> getReclamosPorEdificio(Edificio edificio){
		try {
			Session s=HibernateUtil.getSessionFactory().getCurrentSession();
			s.beginTransaction();
			List<ReclamoEntity> recEntity=(List<ReclamoEntity>)s.createSQLQuery("select * from reclamos where codigo=:codEdificio").addEntity(ReclamoEntity.class).setParameter("codEdificio", edificio.getCodigo()).list();
			s.getTransaction().commit();
			List<Reclamo> reclamosRes= new ArrayList<Reclamo>();
			for(ReclamoEntity i:recEntity) {
				reclamosRes.add(toNegocio(i));
			}
			s.close();
			return reclamosRes;
		}
		catch(Exception e) {
			System.out.println("Error en getReclamosPorEdificio - ReclamoDAO");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public List<Reclamo> getReclamosPorUnidad(Unidad unidad){
		try {
			Session s=HibernateUtil.getSessionFactory().getCurrentSession();
			s.beginTransaction();
			List<ReclamoEntity> recEntity=(List<ReclamoEntity>)s.createSQLQuery("select * from reclamos where identificador=:codUnid").addEntity(ReclamoEntity.class).setParameter("codUnid", unidad.getId()).list();
			s.getTransaction().commit();
			List<Reclamo> recRes= new ArrayList<Reclamo>();
			for(ReclamoEntity i:recEntity) {
				recRes.add(toNegocio(i));
			}
			s.close();
			return recRes;
		}
		catch(Exception e) {
			System.out.println("Error en getReclamosPorUnidad - ReclamoDAO");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Reclamo> getReclamosPorPersona(Persona persona){
		try {
			Session s=HibernateUtil.getSessionFactory().getCurrentSession();
			s.beginTransaction();
			List<ReclamoEntity> recEntity=(List<ReclamoEntity>)s.createSQLQuery("select * from reclamos where documento=:dni").addEntity(ReclamoEntity.class).setParameter("dni", persona.getDocumento()).list();
			s.getTransaction().commit();
			List<Reclamo> recRes= new ArrayList<Reclamo>();
			for(ReclamoEntity i:recEntity) {
				recRes.add(toNegocio(i));
			}
			s.close();
			return recRes;
		}
		catch(Exception e) {
			System.out.println("Error en getReclamosPorPersona - ReclamoDAO");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	static Reclamo toNegocio(ReclamoEntity entity) {
		return new Reclamo(PersonaDAO.toNegocio(entity.getUsuario()), EdificioDAO.toNegocio(entity.getEdificio()), entity.getUbicacion(),entity.getDescripcion(), UnidadDAO.toNegocio(entity.getUnidad()));
	}
	
	static ReclamoEntity toEntity(Reclamo recl) {
		   return new ReclamoEntity(PersonaDAO.toEntity(recl.getUsuario()), EdificioDAO.toEntity(recl.getEdificio()), recl.getUbicacion(), recl.getDescripcion(), UnidadDAO.toEntity(recl.getUnidad()));
	   }
}
