/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import modelo.HibernateUtil;
import modelo.Libro;
import modelo.Persona;
import org.hibernate.Session;

/**
 *
 * @author pablo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Creo libros
        Libro libro1 = new Libro();
        libro1.setTitulo("Todo Java");
        
        Libro libro2 = new Libro();
        libro2.setTitulo("Todo JS");
        
        Libro libro3 = new Libro();
        libro3.setTitulo("Todo TS");
        
        Libro libro4 = new Libro();
        libro4.setTitulo("Todo Hibernate");
        
        // Creo Personas
        Persona persona1 = new Persona();
        persona1.setNombre("Federico");
        persona1.aniadirLibro(libro1);
        persona1.aniadirLibro(libro2);
        
        Persona persona2 = new Persona();
        persona2.setNombre("Maria Paula");
        persona2.aniadirLibro(libro3);
        persona2.aniadirLibro(libro4);
        
        // Sesión para guardar las personas, los libros van en cascada
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        
        sesion.persist(persona1);
        sesion.persist(persona2);
        
        sesion.getTransaction().commit();
        sesion.close();
        
        // Sesión 2 para eliminar la persona 1, sus libros asociados se eliminan
        // en cascada
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();

        sesion.delete(persona1);

        sesion.getTransaction().commit();
        sesion.close();
        
    }
    
}
