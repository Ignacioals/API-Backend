package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.event.SaveOrUpdateEvent;

import dao.PersonaDAO;
import entities.EdificioEntity;
import entities.PersonaEntity;
import entities.UnidadEntity;
import hibernate.HibernateUtil;
import modelo.Edificio;
import modelo.Unidad;


public class UnidadDAO {
	
	public List<Unidad> getUnidades(){
		List<Unidad> auxRes=new ArrayList<Unidad>();
		try {
    		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction ts = session.beginTransaction();
			ts.begin();
			List<UnidadEntity> results=session.createQuery("from UnidadEntity").list();
	        ts.commit();
	        //session.close();   ME TIRA ERROR COMO QUE YA LA CERRE
	        for(UnidadEntity i:results) {
	        	auxRes.add(toNegocio(i));
	        }
		} catch (Exception e) {
			System.out.println("Error en getUnidades() de UnidadDAO");
			e.printStackTrace();
		}
		return auxRes;
	}
	
    public Unidad getUnidad(int codigo) {
    	try {
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		session.beginTransaction();
			UnidadEntity unidadEntity = (UnidadEntity) session.createSQLQuery("SELECT * FROM unidades WHERE identificador = :uId").addEntity(UnidadEntity.class).setParameter("uId", codigo).uniqueResult();
    		Unidad unidad = toNegocio(unidadEntity);
			session.close();
			return unidad;
		} catch (Exception np) {
			System.out.println("No existe una unidad para dicho codigo, piso y numero");
			return null;
		}
    }
    
	public static void save(Unidad unidad) {
		try {
			UnidadEntity aGrabar= toEntity(unidad);
			Session sSave= HibernateUtil.getSessionFactory().getCurrentSession();
			sSave.beginTransaction();
			sSave.saveOrUpdate(aGrabar);
			sSave.getTransaction().commit();
			//sSave.close();
		}
		catch(Exception e) {
			System.out.println("No funciona el save - UnidadDAO");
			e.printStackTrace();
		}
	}
	
	static Unidad toNegocio(UnidadEntity entity) {
		return new Unidad(entity.getId(), entity.getPiso(), entity.getNumero(), entity.estaHabitado(), EdificioDAO.toNegocio(entity.getEdificio()), PersonaDAO.toNegocioListas(entity.getDuenios()),PersonaDAO.toNegocioListas(entity.getInquilinos()));
	}
	
	static UnidadEntity toEntity(Unidad objeto) {
		return new UnidadEntity(objeto.getId(), objeto.getPiso(), objeto.getNumero(), objeto.estaHabitado(), EdificioDAO.toEntity(objeto.getEdificio()), PersonaDAO.toEntityListas(objeto.getDuenios()),PersonaDAO.toEntityListas(objeto.getInquilinos()));
	}
	
	static Unidad toNegocioEdifParam(UnidadEntity unidadEntity, Edificio edificio) {
		return new Unidad(unidadEntity.getId(), unidadEntity.getPiso(), unidadEntity.getNumero(), unidadEntity.estaHabitado(), edificio, PersonaDAO.toNegocioListas(unidadEntity.getDuenios()),PersonaDAO.toNegocioListas(unidadEntity.getInquilinos()));
	}
	
}
