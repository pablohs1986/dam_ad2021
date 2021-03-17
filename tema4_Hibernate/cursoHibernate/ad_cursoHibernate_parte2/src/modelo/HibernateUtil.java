/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 *
 * @author pablo
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
