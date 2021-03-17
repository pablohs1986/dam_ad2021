/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import modelo.CadenaTelevisiva;
import modelo.HibernateUtil;
import modelo.Televidente;
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
        
        // Creo cadenas
        CadenaTelevisiva cadena1 = new CadenaTelevisiva();
        cadena1.setNombre("La 1");
        
        CadenaTelevisiva cadena2 = new CadenaTelevisiva();
        cadena2.setNombre("La 2");
        
        CadenaTelevisiva cadena3 = new CadenaTelevisiva();
        cadena3.setNombre("La 4");
        
        // Las almaceno en la bbdd
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        sesion.save(cadena1);
        sesion.save(cadena2);
        sesion.save(cadena3);
        sesion.getTransaction().commit();
        sesion.close();
        
        // Creo televidentes
        Televidente televidente1 = new Televidente();
        televidente1.setNombre("Pepín");
        televidente1.setCadenaFavorita(cadena1);
        
        Televidente televidente2 = new Televidente();
        televidente2.setNombre("Martita");
        televidente2.setCadenaFavorita(cadena2);
        
        // Los almaceno en la bbdd
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        sesion.save(televidente1);
        sesion.save(televidente2);
        sesion.getTransaction().commit();
        sesion.close();
    }
    
}
