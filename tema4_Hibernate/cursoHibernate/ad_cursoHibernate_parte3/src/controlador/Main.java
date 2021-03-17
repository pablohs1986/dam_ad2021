package controlador;

import modelo.Direccion;
import modelo.HibernateUtil;
import modelo.Persona;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Creo personas y direcciones
        Persona persona1 = new Persona();
        persona1.setNombre("Eduardo Chapulin");
        
        Persona persona2 = new Persona();
        persona2.setNombre("Jose Luis Docker");
        
        Direccion direccion1 = new Direccion();
        direccion1.setCalle("Avenida del Visual Basic");
        direccion1.setCodigoPostal("33006");
        
        Direccion direccion2 = new Direccion();
        direccion2.setCalle("Calle del MongoDBismo");
        direccion2.setCodigoPostal("33004");

        persona1.setDireccion(direccion1);
        persona2.setDireccion(direccion2);
        
        // Inicio factoría
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        // Dirección para comprobar que las personas toman el mismo identificador que las direcciones
//        Direccion d = new Direccion();
//        d.setCalle("Prueba identificadores");
//        d.setCodigoPostal("11111");
        
        // Almacenado en cascada de personas y direcciones
        session.beginTransaction();
//        session.persist(d);
        session.persist(persona1);
        session.persist(persona2);
        session.getTransaction().commit();
        session.close();
    }
    
}
