package test;

import java.util.List;

import controlador.Controlador;
import dao.EdificioDAO;
import views.PersonaView;

public class Test {

	public static void main(String[] args) {
	
		Controlador c= Controlador.getInstancia();
		
		/* FUNCIONA PERFECTO*
		System.out.println("Todos los edificios: ");
		System.out.println(c.getEdificios());
		*/
	//--------------------------------------------------------------------------
		 /*FUNCIONA PERFECTO*
		System.out.println("Unidades por edificio:"); 
		try {
		  //c.getUnidadesPorEdificio(3);
		System.out.println(c.getUnidadesPorEdificio(3)+ "\n");
		  //getUnidadesPorEdificio = true; 
		} 
		catch (Exception e) {
		System.out.println("No anda getUnidadesPorEdificio"); 
		e.printStackTrace();
		}*/
		 
	//--------------------------------------------------------------------------	
		/* FUNCIONA CREO(?
		System.out.println("Habilitado por edificio:");
		try {
			List<PersonaView> res = c.habilitadosPorEdificio(1);
			for(PersonaView ps:res) {
				System.out.println(ps);
			}	
		}
		catch(Exception e) {
			System.out.println("No anda habilitadoPorEdificio");
			e.printStackTrace();
		}
		*/
	//--------------------------------------------------------------------------
		
		/* FUNCIONA CREO(?*
		System.out.println("Duenios por edificio: ");
		try {
			List<PersonaView> res=c.dueniosPorEdificio(2);
			for(PersonaView p:res) {
				System.out.println(p);
			}
		}
		catch(Exception e) {
			System.out.println("No anda dueniosPorEdificio");
			e.printStackTrace();
		}
		*/
	//--------------------------------------------------------------------------	
		/* FUNCIONA CREO(?*
		System.out.println("Habitantes Por Edificio: ");
		try {
			List<PersonaView> res=c.habitantesPorEdificio(2);
			for(PersonaView p:res) {
				System.out.println(p);
			}
		}
		catch(Exception e) {
			System.out.println("No anda dueniosPorEdificio");
			e.printStackTrace();
		}
		*/
	//--------------------------------------------------------------------------			
		
		/* FUNCIONA PERFECTO*
		System.out.println("dueniosPorUnidad: ");
		try {
			System.out.println(c.dueniosPorUnidad(1,"10","6"));
		}
		catch(Exception e) {
			System.out.println("No anda dueniosPorUnidad");
			e.printStackTrace();
		}
		*/
	//--------------------------------------------------------------------------			
		
		/* FUNCIONA PERFECTO*
		System.out.println("inquilinosPorUnidad: ");
		try {
			System.out.println(c.inquilinosPorUnidad(1,"10","6"));
		}
		catch(Exception e) {
			System.out.println("No anda inquilinosPorUnidad");
			e.printStackTrace();
		}
		*/
	//--------------------------------------------------------------------------			
		
		/* FUNCIONA PERFECTO
		System.out.println("alquilarUnidad: ");
		try {
			System.out.println(c.alquilarUnidad(1, "10", "6"));
		}
		catch(Exception e) {
			System.out.println("No anda inquilinosPorUnidad");
			e.printStackTrace();
		}
		*/
	//--------------------------------------------------------------------------	
		
		/* FUNCIONA PERFECTO
		System.out.println("agregarPersona: ");
		try {
			c.agregarPersona("DNI41897582", "Esquerdo, Micaela");
		}
		catch(Exception e) {
			System.out.println("No anda agregarPersona");
			e.printStackTrace();
		}
		*/
	//--------------------------------------------------------------------------	
		/* FUNCIONA PERFECTO!!!!! LOS 2!!!
		System.out.println("agregarInquilinoUnidad: ");
		try {
			c.agregarInquilinoUnidad(1, "10", "6", "DNI41897582");
			//System.out.println("se agrego el inquilino");
			//c.alquilarUnidad(1, "10", "6", "DNI41897582");
		}
		catch(Exception e) {
			System.out.println("No anda agregarInquilinoUnidad");
			e.printStackTrace();
		}
		*/
	//--------------------------------------------------------------------------		
		
		/* FUNCIONA no hay reclamos cargados PROBAR CUANDO HAYA CARGADOS
		System.out.println("reclamos Por edificio: ");
		try {
			c.reclamosPorEdificio(1);
		}
		catch(Exception e) {
			System.out.println("No anda agregarInquilinoUnidad");
			e.printStackTrace();
		}*/
		
	//--------------------------------------------------------------------------
		
		/*FUNCIONA PERF
		System.out.println("eliminarPersona: ");
		try {
			c.agregarPersona("DNI41872517", "Kireo, Yao"); //agrego para borrar
			c.eliminarPersona("DNI41872517");
		}
		catch(Exception e) {
			System.out.println("No anda eliminarPersona");
			e.printStackTrace();
		}
		*/
		
	//--------------------------------------------------------------------------		
		/*//FUNCIONA PERF
		System.out.println("agregar Reclamo: ");
		try {
			System.out.println("a ver si funca: ");
			c.agregarReclamo(1, "10", "6", "DNI30012288", "cocina", "el canio de la bacha esta roto");
		}
		catch(Exception e) {
			System.out.println("No anda agregarReclamo");
			e.printStackTrace();
		}
		*/
		
	//--------------------------------------------------------------------------
		
		/*System.out.println("reclamosPorUnidad: ");
		try {
			System.out.println(c.reclamosPorUnidad(1, "10", "6"));
			//c.agregarReclamo(1, "10", "6", "DNI30012288", "cocina", "el canio de la bacha esta roto");
		}
		catch(Exception e) {
			System.out.println("No anda reclamosPorUnidad");
			e.printStackTrace();
		}
		
		System.out.println("se ejecuto");*/
		
	//--------------------------------------------------------------------------
		
		
		
		
		
		
	}

}
