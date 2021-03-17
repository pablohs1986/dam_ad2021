/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author pablo
 */
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (HibernateException he) {
            System.out.println("Error al iniciar la factoría.");
            throw new ExceptionInInitializerError(he);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
