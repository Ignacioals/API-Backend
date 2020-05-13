package dao;

import java.util.List;

import org.hibernate.classic.Session;

import entities.ImagenEntity;
import entities.ReclamoEntity;
import hibernate.HibernateUtil;
import modelo.Imagen;
import modelo.Reclamo;

public class ImagenDAO {
	
	List<Imagen> imagenes;
	
	
	public static void save(Imagen imagen, int nroReclamo) {
		try {
			Reclamo objetoReclamo=ReclamoDAO.getReclamo(nroReclamo);
			ImagenEntity aGrabar= toEntity(imagen, objetoReclamo);
			Session s= HibernateUtil.getSessionFactory().openSession();
			s.beginTransaction();
			s.save(aGrabar);
			s.getTransaction().commit();
			s.close();
		}
		catch(Exception e) {
			System.out.println("No funciona el save - ImagenDAO");
			e.printStackTrace();
		}
	}
	
	
	
	
	// en este me faltaria hacer referencia al reclamo
	static Imagen toNegocio(ImagenEntity entity) {
		return new Imagen(entity.getDireccion(), entity.getTipo());
	}
	
	static ImagenEntity toEntity(Imagen imag, Reclamo rec) {
		return new ImagenEntity(imag.getDireccion(),imag.getTipo(), ReclamoDAO.toEntity(rec));
	}
	
	
}
