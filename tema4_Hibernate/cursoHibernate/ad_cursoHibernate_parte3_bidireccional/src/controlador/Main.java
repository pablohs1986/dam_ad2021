package controlador;

import modelo.HibernateUtil;
import modelo.Pais;
import modelo.Presidente;
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
        
        // Creo pais
        Pais pais1 = new Pais();
        pais1.setNombre("España");
        
        // Creo presidente
        Presidente presidente1 = new Presidente();
        presidente1.setNombre("Pedrín");
        
        // Asigno presidente a país
        pais1.setPresidente(presidente1);
        
        // Creo factoría
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        // Persisto
        session.beginTransaction();
        session.persist(pais1);
        session.getTransaction().commit();
        session.close();
    }
    
}
